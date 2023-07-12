package com.feidian.service.impl;

import com.feidian.bo.UserBO;
import com.feidian.dto.LoginDTO;
import com.feidian.dto.SignupDTO;
import com.feidian.dto.UserDTO;
import com.feidian.mapper.*;
import com.feidian.po.*;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.util.AESUtil;
import com.feidian.util.JwtUtil;
import com.feidian.util.ReceivingFileUtil;
import com.feidian.util.SecurityContextUtils;
import com.feidian.vo.UserHomepageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.feidian.enums.HttpCodeEnum.REQUIRE_USERNAME;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderCommodityMapper orderCommodityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public ResponseResult fastSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到25之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        String regexPwd = "\\w{8,25}";
        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(REQUIRE_USERNAME);
        }

        if (signupDTO.getPassword().matches(regexPwd) == false) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.errorResult(403, "第二次输入密码与第一次不同");
        }

        //加密密码并创建用户
        //补全16位
        String encryptUserPwd = null;

        encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword()).getData().toString();
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmailAddress());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "快速注册成功");
    }

    @Transactional
    @Override
    public ResponseResult emailSignup(SignupDTO signupDTO) {
        //Todo 校验密码是否符合强度要求
        // 1.只能包含英文字母、阿拉伯数字和下划线
        // 2.密码长度在8到16之间
        // 3.再次输入密码需与第一次输入的密码一致
        // 4.加密密码
        // 5.邮箱验证
        String regexPwd = "\\w{8,16}";

        if (signupDTO.getUsername()==null){
            return ResponseResult.errorResult(REQUIRE_USERNAME);
        }

        if (false == signupDTO.getPassword().matches(regexPwd)) {
            return ResponseResult.errorResult(403,"密码不符合要求");
        }

        if (!signupDTO.getPassword().equals(signupDTO.getRePwd())) {
            return ResponseResult.successResult(403, "第二次输入密码与第一次不同");
        }

        String regexEmailAddress = "\\w+@[\\w&&[^_]]{2,7}(\\.[a-zA-Z]{2,4}){1,3}";

        if (!signupDTO.getEmailAddress().matches(regexEmailAddress)) {
            return ResponseResult.errorResult(403,"邮箱格式不正确");
        }

        //验证邮箱验证码
        Boolean verifyResult = ((String)redisTemplate.opsForValue().get(signupDTO.getUsername()+ "verifyCode")).equals(signupDTO.getVerifyCode());

        if (false == verifyResult) {
            return  ResponseResult.errorResult(403,"验证码错误");
        }

        String encryptUserPwd = getEncryptUserPwd(signupDTO.getPassword()).getData().toString();
        UserBO userBO = new UserBO(signupDTO.getUsername(), encryptUserPwd,
                signupDTO.getNickname(), signupDTO.getEmailAddress());
        userMapper.insertUser(userBO);
        return ResponseResult.successResult(200, "邮箱注册成功");
    }

    @Override
    public ResponseResult login(LoginDTO loginDTO) {
        if (loginDTO.getPassword().length() >16 && loginDTO.getPassword().length() <8 ) {
            return ResponseResult.errorResult(403, "密码不符合要求");
        }

        //密码符合要求则开始验证
        UserPO userPO = userMapper.findByName(loginDTO.getUsername());
        Long id01 = userPO.getId();
        String username01 = userPO.getUsername();

        if (!StringUtils.hasText(username01)) {
            return ResponseResult.errorResult(403,"用户名不存在");
        }

        //验证密码是否正确
        //补全用户输入的密码
        String userPwd = "";
        StringBuilder stringBuilder = new StringBuilder(loginDTO.getPassword());
        if (16 > loginDTO.getPassword().length()){
            for (int i = loginDTO.getPassword().length() ; i < 16; i++) {
                stringBuilder = stringBuilder.append("=");
            }
        }
        userPwd = stringBuilder.toString();

        //获取解密后的密码
        String decryptUserPwd = null;
        try {
            decryptUserPwd = AESUtil.decryptByAES(userPO.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!decryptUserPwd.equals(userPwd)) {
            userPO.setUserStatus(1L);
            return ResponseResult.errorResult(403,"密码不正确");
        }

        //如果正确 生成token返回,并记录日志
        Map<String, Object> map;
        map = new HashMap<>();
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(id01), null);
        map.put("Authorization", token);
        userPO.setUserStatus(0L);

        return ResponseResult.successResult(map);
    }

    @Transactional
    @Override
    public ResponseResult updateUserHead(MultipartFile headFile) {
        UserBO userBO = new UserBO();
        userBO.setId(SecurityContextUtils.getUserId());

        //接收头像图片
        String headUrl = "";
        String uploadCommodityCoverDir = "D:/uploads/commodities/cover/";
        ReceivingFileUtil.saveFile(headFile, uploadCommodityCoverDir);
        headUrl = ReceivingFileUtil.saveFile(headFile, uploadCommodityCoverDir);
        userBO.setHeadUrl(headUrl);

        //更新数据库
        userMapper.updateUserInfo(userBO);

        return ResponseResult.successResult(200,"修改成功");
    }

    @Transactional
    @Override
    public ResponseResult updateUserDescription(UserDTO userDTO) {
        userDTO.setId(SecurityContextUtils.getUserId());
        UserBO userBO = new UserBO(userDTO.getId(),userDTO.getUserDescription());
        userMapper.updateUserInfo(userBO);
        return ResponseResult.successResult(200,"更新个性签名成功");
    }

    @Override
    public ResponseResult viewUserHomepage() {
        Long userId = SecurityContextUtils.getUserId();

        UserPO userPO = userMapper.findById(userId);

        List<VideoPO> videoPOList = videoMapper.findByUserId(userId);
        List<CommodityPO> commodityPOList = commodityMapper.findByUserId(userId);
        List<OrderPO> buyerOrderVoList = orderMapper.findByBuyerId(userId);
        List<OrderPO> sellerOrderVoList = orderMapper.findBySellerId(userId);
        List<CartPO> cartList = cartMapper.findByUserId(userId);

        UserHomepageVO userHomepageVo = new UserHomepageVO(userId, userPO.getUsername(),
                userPO.getUserDescription(), userPO.getPhone(), userPO.getHeadUrl(),
                userPO.getEmailAddress(), videoPOList, commodityPOList, buyerOrderVoList,sellerOrderVoList, cartList);
        return  ResponseResult.successResult(userHomepageVo);
    }



    //加密用户密码
    public ResponseResult getEncryptUserPwd(String password) {
        String userPwd = "";
        StringBuilder stringBuilder = new StringBuilder(password);
        //补全
        if (16 > password.length()){
            for (int i = password.length() ; i < 16; i++) {
                stringBuilder = stringBuilder.append("=");
            }
        }

        userPwd = stringBuilder.toString();
        String encryptUserPwd = null;
        try {
            encryptUserPwd = AESUtil.encryptByAES(userPwd);
            return ResponseResult.successResult(encryptUserPwd);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
