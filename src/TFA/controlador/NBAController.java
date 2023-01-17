package TFA.controlador;

import TFA.modelo.NBAModel;
import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;
import java.util.Objects;

public class NBAController {
    private NBAModel model;
    private NBAView view;
    private Team teamToDisplay;

    public NBAController(NBAModel model, NBAView view) {
        this.model = model;
        this.view = view;
    }

    public void addActionListenerToTeamsBox(JComboBox<String> teamsComboBox) {
        teamsComboBox.addActionListener(e -> {
            // Si no se selecciona un equipo no pasará nada
            if (Objects.equals(teamsComboBox.getSelectedItem(), "")) {
                return;
            }
            try {
                // TODO Obtener las estadísticas del equipo seleccionado
                // Obtener los jugadores del equipo seleccionado
                System.out.println("Equipo: " + teamsComboBox.getSelectedItem());
                teamToDisplay = model.getTeamByName((String) teamsComboBox.getSelectedItem());
                view.updatePlayersList(model.getPlayersListFromTeam(teamToDisplay.getId()));

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public void setTeamsComboBox(JComboBox<String> teamsComboBox) {
        model.setTeamList();
        teamsComboBox.addItem("");
        for (Team team : model.getTeamsList()) {
            teamsComboBox.addItem(team.getName());
        }
        teamsComboBox.setSelectedIndex(0);
    }
}