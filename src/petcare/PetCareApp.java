package petcare;

import petcare.menu.MainMenu;
import petcare.menu.StartupMenu;

public class PetCareApp {
    public static void main(String[] args) {
        new StartupMenu().run();   // data-init screen (CSV vs raw)
        new MainMenu().run();      // module selection loop
    }
}
