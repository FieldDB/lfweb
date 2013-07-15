package org.palaso.languageforge.client.lex.service.actions;

import org.palaso.languageforge.client.lex.common.BaseConfiguration;
import org.palaso.languageforge.client.lex.jsonrpc.JsonRpcAction;
import org.palaso.languageforge.client.lex.model.UserDto;

public class UpdateUserAccessRoleAction extends JsonRpcAction<UserDto> {

	private UserDto userDto;
	private int projectId;

	public UpdateUserAccessRoleAction(UserDto userDto, int projectId) {
		super(BaseConfiguration.getInstance().getLFApiPath(), BaseConfiguration.getInstance().getApiFileName(), "updateUserRoleGrant", 2);
		this.userDto = userDto;
		this.projectId = projectId;
	}

	public String encodeParam(int i) {
		switch (i) {
		case 0:
			return String.valueOf(projectId);
		case 1:
			return UserDto.encode(userDto);
		}
		return null;
	}
}