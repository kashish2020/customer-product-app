package com.dxctraining.wishlist.util;

import org.springframework.stereotype.Component;

import com.dxctraining.wishlist.dto.WishedItemDto;
import com.dxctraining.wishlist.entities.WishedItem;

@Component
public class WishedItemUtil {

	public WishedItemDto wishedItemDto(WishedItem item, Integer customerId, String customerName, String productId,
			String productName) {
		WishedItemDto dto = new WishedItemDto(item.getId(), item.getCustomerId(), item.getProductId());
		dto.setCustomerId(customerId);
		dto.setCustomerName(customerName);
		dto.setProductId(productId);
		dto.setProductName(productName);

		return dto;

	}
}
