package tms;

import tms.transport.dao.StopDAO;
import tms.transport.dao.VehicleDAO;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        System.out.println("⚪ Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tms");

        System.out.println("⚪ Creazione DAO...");
        Scanner scan = new Scanner(System.in);
        StopDAO stopDAO = new StopDAO(emf);
        VehicleDAO vehicleDAO = new VehicleDAO(emf);

        System.out.println("Cosa vuoi fare?\n1. Gestisci fermate\n2. Gestisci veicoli");
        String scelta = scan.nextLine();

        switch (scelta) {
            case "1":
                System.out.println("Vuoi aggiungere, modificare o cancellare una fermata?\n1. Aggiungi\n2. Modifica\n3. Cancella");
                String azioneFermate = scan.nextLine();
                switch (azioneFermate) {
                    case "1":
                        System.out.println("Inserisci il nome della fermata:");
                        String addName = scan.nextLine();
                        stopDAO.addStop(addName);
                        break;
                    case "2":
                        displayAllStops();
                        System.out.println("Inserisci l'ID della fermata da modificare:");
                        Long editId = scan.nextLong();
                        scan.nextLine();
                        System.out.println("Inserisci il nuovo nome della fermata:");
                        String newName = scan.nextLine();
                        stopDAO.editStop(editId, newName);
                        break;
                    case "3":
                        displayAllStops();
                        System.out.println("Inserisci l'ID della fermata da cancellare:");
                        Long deleteId = scan.nextLong();
                        stopDAO.deleteStop(deleteId);
                        break;
                    default:
                        System.out.println("Azione non riconosciuta.");
                        break;
                }
                break;

            case "2":
                System.out.println("Cosa vuoi fare con i veicoli?\n1. Aggiungi veicolo\n2. Modifica veicolo\n3. Cancella veicolo");
                String azioneVeicoli = scan.nextLine();
                switch (azioneVeicoli) {
                    case "1":
                        System.out.println("Scegli il tipo di veicolo\n1. BUS\n2. TRAM");
                        int vehicleTypeChoice = scan.nextInt();
                        scan.nextLine();

                        String vehicleType;
                        switch (vehicleTypeChoice) {
                            case 1:
                                vehicleType = "BUS";
                                break;
                            case 2:
                                vehicleType = "TRAM";
                                break;
                            default:
                                System.out.println("Scelta non valida.\nScegli il tipo di veicolo\n1. BUS\n2. TRAM");
                                return;
                        }

                        System.out.println("Inserisci la capacità del veicolo: ");
                        int capacity = scan.nextInt();
                        scan.nextLine();
                        vehicleDAO.addVehicle(VehicleDataTypes.valueOf(vehicleType), capacity);
                        break;
                    case "2":
                        displayAllVehicles();
                        System.out.println("Inserisci l'ID del veicolo da modificare: ");
                        Long editVehicleId = scan.nextLong();
                        scan.nextLine();
                        System.out.println("Scegli il tipo di veicolo\n1. BUS\n2. TRAM");
                        String newVehicleType = scan.nextLine();
                        System.out.println("Inserisci la capacità del veicolo: ");
                        int newCapacity = scan.nextInt();
                        scan.nextLine();
                        vehicleDAO.editVehicle(editVehicleId, VehicleDataTypes.valueOf(newVehicleType), newCapacity);
                        break;

                    case "3":
                        displayAllVehicles();
                        System.out.println("Inserisci l'ID del veicolo da cancellare: ");
                        Long deleteVehicleId = scan.nextLong();
                        scan.nextLine();
                        vehicleDAO.deleteVehicle(deleteVehicleId);
                        break;
                    default:
                        System.out.println("Azione non riconosciuta.");
                        break;
                }
                break;

            default:
                System.out.println("Scelta non riconosciuta.");
                break;
        }

        System.out.println("🔴 Chiusura dell'EntityManagerFactory alla fine dell'applicazione");
        emf.close();
    }

    private static void displayAllStops() {
    }

    private static void displayAllVehicles() {
    }
}
