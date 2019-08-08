package br.com.project.convert;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.project.builder.NotifyDataBuilder;
import br.com.project.canonical.EntityCanonical;
import br.com.project.canonical.NotifyCanonical;
import br.com.project.data.Notify;

@Lazy
@Component
public class NotifyCanonicalToNotifyConverter implements Converter<NotifyCanonical, Notify>{

	@Override
	public Notify convert(NotifyCanonical source) {
		if(source.entity() instanceof EntityCanonical) {
			final EntityCanonical canonical = (EntityCanonical) source.entity();
			return new NotifyDataBuilder()
					.setRequestId(canonical.requestId())
					.setDate(source.dateInsert())
					.setType(source.type())
					.build();
		} else {
			return null;
		}
	}

}
