package com.roy.pojo;

import org.mapstruct.Mapper;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 23:07 2021-01-08
 * @history:
 */
@Mapper
public interface OrderMapper {

	OrderQueryParam entity2queryParam(Order order);

}
