package org.palaso.languageforge.client.lex.main.service.actions;

import org.palaso.languageforge.client.lex.jsonrpc.JsonRpcAction;
import org.palaso.languageforge.client.lex.model.ResultDto;
import org.palaso.languageforge.client.lex.common.Constants;

public class DeleteEntryAction extends JsonRpcAction<ResultDto> {

	private String id = "1";
	private String mercurialSHA = "";
	public DeleteEntryAction(String id, String mercurialSHA) {
		super(Constants.LEX_API_PATH, Constants.LEX_API, "deleteEntry", 2);
		this.id = id;
		this.mercurialSHA = mercurialSHA;
	}

	@Override
	public String encodeParam(int i) {
		switch (i) {
		case 0:
			return this.id;
		case 1:
			return this.mercurialSHA;
		}
		return null;
	}

}
