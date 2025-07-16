package school.hei.patrimoine.visualisation.web.layouts;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toSet;
import static school.hei.patrimoine.modele.Devise.EUR;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.cas.CasSet;
import school.hei.patrimoine.cas.ToutCas;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.visualisation.web.service.CasSetAnalyzerService;
import school.hei.patrimoine.visualisation.web.service.WebGrapheurService;

public class MainLayout extends VerticalLayout {
  public MainLayout(
      WebGrapheurService grapheurService,
      CasSetAnalyzerService casSetAnalyzerService,
      CasSet casSet) {
    var firstCas = casSet.set().stream().toList().getFirst();
    var toutCas = new ToutCas(firstCas.getAjd(), firstCas.getFinSimulation(), casSet);
    casSetAnalyzerService.verify(toutCas);
    var patrimoines = new ArrayList<>(List.of(toutCas.patrimoine()));
    patrimoines.addAll(casSet.set().stream().map(Cas::patrimoine).toList());
    patrimoines.addAll(getPersonalPatrimoines(patrimoines));
    setWidthFull();
    add(new H1("Patrimoine"), new PatrimoineEvolutionLayout(grapheurService, patrimoines));
  }

  private Set<Patrimoine> getPersonalPatrimoines(List<Patrimoine> patrimoines) {
    return patrimoines.stream()
        .flatMap(
            e ->
                e.getPossesseurs().keySet().stream()
                    .map(personne -> personne.patrimoine(EUR, now())))
        .collect(toSet());
  }
}
