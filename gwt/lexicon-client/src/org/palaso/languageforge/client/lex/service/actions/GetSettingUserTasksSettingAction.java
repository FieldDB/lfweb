package org.palaso.languageforge.client.lex.service.actions;


import org.palaso.languageforge.client.lex.common.BaseConfiguration;
import org.palaso.languageforge.client.lex.jsonrpc.JsonRpcAction;
import org.palaso.languageforge.client.lex.model.settings.tasks.SettingTasksDto;

public class GetSettingUserTasksSettingAction extends
		JsonRpcAction<SettingTasksDto> {

	int value;

	public GetSettingUserTasksSettingAction(int userId) {
		super(BaseConfiguration.getInstance().getLFApiPath(), BaseConfiguration.getInstance().getApiFileName(), "getUserTasksSetting", 1);
		value = userId;
	}

	@Override
	public String encodeParam(int i) {
		switch (i) {
		case 0:
			return String.valueOf(value);
		}
		return null;
	}

}
