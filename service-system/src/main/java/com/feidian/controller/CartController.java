package com.feidian.controller;


import com.feidian.dto.CartDTO;
import com.feidian.dto.PurchaseDTO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    //上传到购物车
    @PostMapping("/uploadCart")
    @ResponseBody
    public ResponseResult uploadCart(@RequestBody CartDTO cartDTO){
        return cartService.uploadCart(cartDTO);
    }

    //展示购物车列表
    @GetMapping("/displayCartVOList")
    public ResponseResult displayCartVOList() {
        return cartService.displayCartVOList();
    }

    //删除购物车
    @PostMapping("/deleteCart")
    public ResponseResult deleteCart(long cartId){
        return cartService.deleteCart(cartId);
    }

    //从购物车中购买（暂时设计兼容直接购买）
    @PostMapping("/cartPurchase")
    public ResponseResult cartPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return cartService.cartPurchase(purchaseDTO);
    }



}
