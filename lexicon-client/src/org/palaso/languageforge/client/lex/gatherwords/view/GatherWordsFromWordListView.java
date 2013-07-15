package org.palaso.languageforge.client.lex.gatherwords.view;

import org.palaso.languageforge.client.lex.gatherwords.presenter.GatherWordsFromWordListPresenter.IGatherWordsFromWordListView;
import org.palaso.languageforge.client.lex.model.LexiconEntryDto;
import org.palaso.languageforge.client.lex.presenter.EntryPresenter.IEntryView;
import org.palaso.languageforge.client.lex.presenter.MultiTextPresenter.IMultiTextView;
import org.palaso.languageforge.client.lex.view.EntryView;
import org.palaso.languageforge.client.lex.view.MultiTextView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;


@Singleton
public class GatherWordsFromWordListView extends Composite implements
		IGatherWordsFromWordListView {

	interface Binder extends UiBinder<Widget, GatherWordsFromWordListView> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Label lblTitle;

	@UiField
	MultiTextView mutTxtSample;

	@UiField(provided = true)
	EntryView dictEditView = new EntryView();

	@UiField(provided = true)
	CellTable<LexiconEntryDto> cellTable = new CellTable<LexiconEntryDto>();

	@UiField
	Button btnPre;
	@UiField
	Button btnNext;
	@UiField
	Button btnAddWord;

	public GatherWordsFromWordListView() {
		initWidget(binder.createAndBindUi(this));
	}

	public Widget getWidget() {
		return this.asWidget();
	}

	@Override
	public IMultiTextView getSimpleMultiText() {
		return mutTxtSample;
	}

	@Override
	public IEntryView getDictEditView() {
		return dictEditView;
	}

	@Override
	public void setPreBtnEnabled(boolean enabled) {
		btnPre.setEnabled(enabled);

	}

	@Override
	public void setNextBtnEnabled(boolean enabled) {
		btnNext.setEnabled(enabled);

	}

	@Override
	public void setAddWordBtnEnabled(boolean enabled) {
		btnAddWord.setEnabled(enabled);

	}

	@Override
	public HasClickHandlers preBtnClickHandlers() {
		return btnPre;
	}

	@Override
	public HasClickHandlers nextBtnClickHandlers() {
		return btnNext;
	}

	@Override
	public HasClickHandlers addWordBtnClickHandlers() {
		return btnAddWord;
	}
	
	@Override
	public CellTable<LexiconEntryDto> getRecentlyAddedTable()
	{
		return cellTable;
	}

}