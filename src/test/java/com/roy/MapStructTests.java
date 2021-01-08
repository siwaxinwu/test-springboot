package com.roy;

import com.roy.entity.UserInfo;
import com.roy.pojo.Order;
import com.roy.pojo.OrderMapper;
import com.roy.pojo.OrderQueryParam;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapStructTests {

	@Test
	public void entity2queryParam() {
		Order order = new Order();
		order.setId(12345L);
		order.setOrderSn("orderSn");
		order.setOrderType(0);
		order.setReceiverKeyword("keyword");
		order.setSourceType(1);
		order.setStatus(2);

		OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
		OrderQueryParam orderQueryParam = mapper.entity2queryParam(order);
		assertEquals(orderQueryParam.getOrderSn(), order.getOrderSn());
		assertEquals(orderQueryParam.getOrderType(), order.getOrderType());
		assertEquals(orderQueryParam.getReceiverKeyword(), order.getReceiverKeyword());
		assertEquals(orderQueryParam.getSourceType(), order.getSourceType());
		assertEquals(orderQueryParam.getStatus(), order.getStatus());
		//assertEquals(1, 2);


	}


}
