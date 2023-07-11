package com.feidian.service;

import com.feidian.dto.AddressDTO;
import com.feidian.responseResult.ResponseResult;

public interface AddressService {
    ResponseResult uploadUserAddress(AddressDTO addressDTO);

    ResponseResult deleteAddress(long addressId);

    ResponseResult updateAddressInfo(AddressDTO addressDTO);
}
