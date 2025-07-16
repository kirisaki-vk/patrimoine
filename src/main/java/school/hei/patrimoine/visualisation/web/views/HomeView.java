package school.hei.patrimoine.visualisation.web.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import school.hei.patrimoine.cas.CasSetSupplier;
import school.hei.patrimoine.visualisation.web.layouts.MainLayout;
import school.hei.patrimoine.visualisation.web.service.CasSetAnalyzerService;
import school.hei.patrimoine.visualisation.web.service.WebGrapheurService;

@Route("")
public class HomeView extends Div {

  public HomeView(
      WebGrapheurService webGrapheurService, CasSetAnalyzerService casSetAnalyzerService) {
    setClassName("home-view");
    add(new MainLayout(webGrapheurService, casSetAnalyzerService, new CasSetSupplier().get()));
  }
}
