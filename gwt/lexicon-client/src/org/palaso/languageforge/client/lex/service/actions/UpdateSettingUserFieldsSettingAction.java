package org.palaso.languageforge.client.lex.service.actions;

import org.palaso.languageforge.client.lex.common.BaseConfiguration;
import org.palaso.languageforge.client.lex.jsonrpc.JsonRpcAction;
import org.palaso.languageforge.client.lex.model.settings.fields.SettingFieldsDto;

public class UpdateSettingUserFieldsSettingAction extends
		JsonRpcAction<SettingFieldsDto> {


	SettingFieldsDto value;
	String userId;
	
	public UpdateSettingUserFieldsSettingAction(String userId, SettingFieldsDto value) {
		super(BaseConfiguration.getInstance().getLFApiPath(), BaseConfiguration.getInstance().getApiFileName(), "updateSettingFields",2);
		this.value = value;
		this.userId = userId;
	}

	
	@Override
	public String encodeParam(int i) {
		switch (i) {
		case 0:
			return userId;
		case 1:
			return SettingFieldsDto.encode(value);
		}
		return null;
	}

}
