package arithmea.client.view;

import arithmea.client.presenter.InfoPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * View for the info page.
 */
public class InfoView extends Composite implements InfoPresenter.Display {
    private final Button cancelButton;
    private final FlexTable contentTable;

    /**
     * Default constructor.
     */
    public InfoView() {
        final DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("800px");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");

        final HorizontalPanel menuPanel = new HorizontalPanel();
        cancelButton = new Button("Cancel");
        menuPanel.add(cancelButton);

        contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
        contentTable.setWidget(0, 0, menuPanel);

        final FlexTable infoTable = new FlexTable();
        int row = 0;
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "== Methods working on the latin alphabet ==");
        infoTable.setText(row++, 0, "The following methods work on the 26 letters that are used in the latin (or english) alphabet.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Chaldean:");
        infoTable.setText(row++, 0, "Shows the value of the word in chaldean numerology. The result is not reduced in order to preserve the full information.");
        final String chaldeanWikiUrl = "http://en.wikipedia.org/wiki/Arithmancy#The_Chaldean_method";
        infoTable.setWidget(row++, 0, new Anchor(chaldeanWikiUrl, chaldeanWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Pythagorean:");
        infoTable.setText(row++, 0, "Shows the value of the word in pythagorean (or agrippan) numerology. The result is not reduced in order to preserve the full information.");
        final String pythWikiUrl = "http://en.wikipedia.org/wiki/Arithmancy#The_Agrippan_method";
        infoTable.setWidget(row++, 0, new Anchor(pythWikiUrl, pythWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "IA:");
        infoTable.setText(row++, 0, "1=A, 2=B, 3=C .. 26=Z. Also known as Simple English Gematria.");
        final String segWikiUrl = "http://wmjas.wikidot.com/simple-english-gematria";
        infoTable.setWidget(row++, 0, new Anchor(segWikiUrl, segWikiUrl));
        infoTable.setText(row++, 0, "There is another system of english gematria, that uses the sixfold of every letter: A=6, B=12, C=18 .. Z=156. It matches the same words as simple gematria, but yields higher values. ArithmeA prefers simple english gematria, because it can output odd numbers and therefore preserves the original number qualities better.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "NAEQ:");
        infoTable.setText(row++, 0, "New Aeon English Qabalah");
        final String naeqWikiUrl = "http://en.wikipedia.org/wiki/English_Qabalah#ALW_Cipher";
        infoTable.setWidget(row++, 0, new Anchor(naeqWikiUrl, naeqWikiUrl));
        infoTable.setText(row++, 0, "The NAEQ Cipher can be obtained, by arranging the letters of the alphabet on an elevenfold star.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "TQ:");
        infoTable.setText(row++, 0, "Trigrammaton Qabalah");
        final String tqWikiUrl = "http://en.wikipedia.org/wiki/English_Qabalah#Trigrammaton_Qabalah_.28TQ.29";
        infoTable.setWidget(row++, 0, new Anchor(tqWikiUrl, tqWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "German:");
        infoTable.setText(row++, 0, "A cipher specific to the german language, that was discovered by Heinz Borchardt.");
        final String gerUrl = "http://www.rolf-keppler.de/schluessel.htm";
        infoTable.setWidget(row++, 0, new Anchor(gerUrl, gerUrl));
        infoTable.setText(row++, 0, "The interpretation of the results for this cipher is very different from the other methods and generally leads to low numbers and a lot of matches because most letters have a value of 0. The number obtained by applying this method is supposed to directly describe the quality of german words.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "EQ:");
        infoTable.setText(row++, 0, "English Qabalah method that uses the same sequence as NAEQ, but without reducing the Values. This method is also known as EQ26 or Azure Lidded Woman Cipher (thanks to Alien696).");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "== Methods working on the hebrew transliteration ==");
        infoTable.setText(row++, 0, "In a first step, the words are transliterated into a representation with hebrew letters according to a predefined method. The method of transliteration is misusing hebrew consonants as if they were vowels.");
        infoTable.setText(row++, 0, "Transliteration is different from translation and results in words and accumulations of letters, that most likey don't have any meaning in hebrew. There are different possibilities on how the transliteration may be performed.");
        final String transliterationdUrl = "http://en.wikipedia.org/wiki/Romanization_of_Hebrew#How_to_transliterate";
        infoTable.setWidget(row++, 0, new Anchor(transliterationdUrl, transliterationdUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setText(row++, 0, "If you want to know the exact method, you may have a look at the sourcecode of the class arithmea.shared.gematria.GematriaUtil in the repository that is referenced at the bottom of this page.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Full:");
        infoTable.setText(row++, 0, "Uses absolute or normative numerical value of the twenty-two hebrew letters. This method may be refered to as Mispar Hechrachi or Mispar ha-Panim.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Ordinal:");
        infoTable.setText(row++, 0, "Each of the 22 hebrew letters are given a value from one to twenty-two. Also known as Mispar Siduri.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Katan:");
        infoTable.setText(row++, 0, "Calculates the value of each hebrew letter, but truncates all of the zeros. It is also sometimes called Mispar Me'ugal.");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        final String methodUrl = "http://en.wikipedia.org/wiki/Gematria#Methods";
        infoTable.setWidget(row++, 0, new Anchor(methodUrl, methodUrl));

        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Project page and source-code for ArithmeA 2000:");
        final String projectUrl = "http://tok.github.com/ArithmeA-2000/";
        infoTable.setWidget(row++, 0, new Anchor(projectUrl, projectUrl));

        for (int r = 0; r < row; r++) {
            infoTable.getCellFormatter().setStyleName(r, 0, "padded");
        }
        contentTable.setWidget(1, 0, infoTable);
        contentTable.getCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
        contentTable.getCellFormatter().setHeight(1, 0, "635px");

        contentTableDecorator.add(contentTable);
    }

    @Override
    public final HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public final Widget asWidget() {
        return this;
    }

}
