package com.estore.ProductsService.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estore.ProductsService.entity.ProductLookupEntity;
import com.estore.ProductsService.repository.ProductLookupRepository;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	private static final Logger log = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	@Autowired
	private ProductLookupRepository productLookupRepository;

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		return (index, command) -> {
			log.debug("command type : " + command.getPayloadType());
			log.debug("command payload : " + command.getPayload());
			if (CreateProductCommand.class.equals(command.getPayloadType())) {
				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

				ProductLookupEntity productLookupEntity = productLookupRepository
						.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
				if(Objects.nonNull(productLookupEntity))
					throw new IllegalStateException("Product Already Exists in DB");

				if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0)
					throw new IllegalArgumentException("Price can not be less than zero");
				if (StringUtils.isBlank(createProductCommand.getTitle()))
					throw new IllegalArgumentException("Title cannot be Blank");
				;
			}
			return command;
		};
	}

}
