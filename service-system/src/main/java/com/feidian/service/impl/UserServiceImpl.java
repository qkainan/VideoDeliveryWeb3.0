package com.feidian.service.impl;


import com.feidian.bo.UserBO;
import com.feidian.dto.UserDTO;
import com.feidian.mapper.*;
import com.feidian.po.*;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.UserService;
import com.feidian.util.AESUtil;
import com.feidian.util.ReceivingFileUtil;
import com.feidian.util.SecurityContextUtils;
import com.feidian.vo.UserHomepageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

        //TODO
        SysUser user = userMapper.selectById(userId);

        List<VideoPO> videoPOList = videoMapper.findByUserId(userId);
        List<CommodityPO> commodityPOList = commodityMapper.findByUserId(userId);
        List<OrderPO> buyerOrderVoList = orderMapper.findByBuyerId(userId);
        List<OrderPO> sellerOrderVoList = orderMapper.findBySellerId(userId);
        List<CartPO> cartList = cartMapper.findByUserId(userId);

        UserHomepageVO userHomepageVo = new UserHomepageVO(userId, user.getUsername(),
                user.getPhonenumber(), user.getAvatar(), user.getEmail(),
                videoPOList, commodityPOList, buyerOrderVoList, sellerOrderVoList, cartList);
        return ResponseResult.successResult(userHomepageVo);
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
