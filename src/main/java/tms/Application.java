package tms;

import jdk.jshell.JShell;
import net.bytebuddy.asm.Advice;
import tms.transport.dao.*;
import tms.transport.entities.*;
import tms.transport.enums.SubscriptionPeriodicityDataTypes;
import tms.transport.enums.TicketProviderDataTypes;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("⚪ Avvio Entity Manager Factory (emf).");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tms");
        EntityManager em = emf.createEntityManager();
        System.out.println("⚪ Avvio Entity Manager tramite Entity Manager Factory.");
        Scanner scan = new Scanner(System.in);
        StopDAO stopDAO = new StopDAO(emf);
        VehicleDAO vehicleDAO = new VehicleDAO(emf);
        RouteDAO routeDAO = new RouteDAO(emf);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(emf);
        TicketDAO ticketDAO = new TicketDAO(em);

        Ticket ticket1 = new Ticket(LocalDate.of(2023, 1, 1), null);
        while (true) {
            System.out.println("Sei un dipendente o un passeggero? \n1. Dipendente \n2. Passeggero");
            String mainChoise = scan.nextLine();
            switch (mainChoise) {
                case "1":
                    while (true) {
                        System.out.println("Cosa vuoi fare?\n1. Gestisci fermate\n2. Gestisci veicoli\n3. Gestisci rotte\n4. Gestisci manutenzioni\n5. Esci");
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
                                        stopDAO.displayAllStops();
                                        System.out.println("Inserisci l'ID della fermata da modificare:");
                                        Long editId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Inserisci il nuovo nome della fermata:");
                                        String newName = scan.nextLine();
                                        stopDAO.editStop(editId, newName);
                                        break;
                                    case "3":
                                        stopDAO.displayAllStops();
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
                                        vehicleDAO.displayAllVehicles();
                                        System.out.println("Inserisci l'ID del veicolo da modificare: ");
                                        Long editVehicleId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Scegli il tipo di veicolo\n1. BUS\n2. TRAM");
                                        int newVehicleTypeChoice = scan.nextInt();
                                        scan.nextLine();
                                        String newVehicleType;
                                        switch (newVehicleTypeChoice) {
                                            case 1:
                                                newVehicleType = VehicleDataTypes.BUS.toString();
                                                break;
                                            case 2:
                                                newVehicleType = VehicleDataTypes.TRAM.toString();
                                                break;
                                            default:
                                                System.out.println("Scelta non valida.\nScegli il tipo di veicolo\n1. BUS\n2. TRAM");
                                                return;
                                        }
                                        System.out.println("Inserisci la capacità del veicolo: ");
                                        int newCapacity = scan.nextInt();
                                        scan.nextLine();
                                        vehicleDAO.editVehicle(editVehicleId, String.valueOf(VehicleDataTypes.valueOf(newVehicleType)), newCapacity);
                                        break;
                                    case "3":
                                        vehicleDAO.displayAllVehicles();
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
                            case "3":
                                System.out.println("Gestione delle rotte:\n1. Aggiungi rotta\n2. Modifica rotta\n3. Cancella rotta\n4. Assegna mezzo\n5. Tempi percorrenza tratte");
                                String azioneRotte = scan.nextLine();
                                switch (azioneRotte) {
                                    case "1":
                                        System.out.println("Creazione di una nuova rotta:");
                                        stopDAO.displayAllStops();
                                        System.out.println("Inserisci l'ID della fermata di partenza:");
                                        Long departureStopId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Inserisci l'ID della fermata di arrivo:");
                                        Long arrivalStopId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Inserisci il tempo di percorrenza medio in minuti:");
                                        int averageTime = scan.nextInt();
                                        scan.nextLine();
                                        routeDAO.addRoute(departureStopId, arrivalStopId, averageTime);
                                        break;
                                    case "2":
                                        routeDAO.displayAllRoutes();
                                        System.out.println("Inserisci l'ID della rotta da modificare:");
                                        Long editRouteId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Operazione per modificare una rotta non ancora implementata.");
                                        break;
                                    case "3":
                                        routeDAO.displayAllRoutes();
                                        System.out.println("Inserisci l'ID della rotta da cancellare:");
                                        long deleteRouteId = scan.nextLong();
                                        scan.nextLine();
                                        routeDAO.deleteRoute(deleteRouteId);
                                        break;
                                    case "4":
                                        System.out.println("Assegnazione di un veicolo a una rotta esistente:");
                                        List<Route> routes = routeDAO.getRoutesList();
                                        routeDAO.displayAllRoutes();
                                        System.out.println("Inserisci il numero corrispondente alla rotta a cui vuoi assegnare un veicolo:");
                                        int selectedRouteNumber = scan.nextInt();
                                        scan.nextLine();
                                        if (selectedRouteNumber >= 1 && selectedRouteNumber <= routes.size()) {
                                            Route selectedRoute = routes.get(selectedRouteNumber - 1);
                                            List<Vehicle> vehicles = vehicleDAO.getVehiclesList();
                                            vehicleDAO.displayAllVehicles();
                                            System.out.println("Inserisci il numero corrispondente al veicolo da assegnare alla rotta:");
                                            int selectedVehicleNumber = scan.nextInt();
                                            scan.nextLine();
                                            if (selectedVehicleNumber >= 1 && selectedVehicleNumber <= vehicles.size()) {
                                                Vehicle selectedVehicle = vehicles.get(selectedVehicleNumber - 1);
                                                routeDAO.assignVehicleToRoute(selectedRoute.getId(), selectedVehicle.getId());
                                                System.out.println("🟢 Veicolo assegnato alla rotta con successo.");
                                            } else {
                                                System.out.println("🔴 Numero veicolo non valido.");
                                            }
                                        } else {
                                            System.out.println("🔴 Numero rotta non valido.");
                                        }
                                        break;
                                    case "5":
                                        System.out.println("Visualizza Average Time di una rotta tramite ID:");
                                        System.out.println("Inserisci l'ID della rotta di cui vuoi visualizzare l'Average Time:");
                                        Long routeIdToCheck = scan.nextLong();
                                        scan.nextLine();
                                        int avgTime = routeDAO.getAvgTime(routeIdToCheck);
                                        if (avgTime != -1) {
                                            System.out.println("Average Time della rotta con ID " + routeIdToCheck + ": " + avgTime + " minuti");
                                        } else {
                                            System.out.println("Rotta con ID " + routeIdToCheck + " non trovata.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Scelta non riconosciuta.");
                                        break;
                                }
                            case "4":
                                System.out.println("Cosa vuoi fare con le manutenzioni?\n1. Aggiungi manutenzione\n2. Modifica manutenzione\n3. Cancella manutenzione\n4. Visualizza manutenzioni per periodo");
                                String azioneManutenzioni = scan.nextLine();
                                switch (azioneManutenzioni) {
                                    case "1":
                                        System.out.println("Aggiungi manutenzione:");
                                        vehicleDAO.displayAllVehicles();
                                        System.out.println("Inserisci l'ID del veicolo:");
                                        Long addVehicleId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Inserisci la data di inizio (dd-mm-yyyy):");
                                        String addStartDateStr = scan.nextLine();
                                        System.out.println("Inserisci la data di fine (dd-mm-yyyy):");
                                        String addEndDateStr = scan.nextLine();
                                        try {
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                            Date addStartDate = dateFormat.parse(addStartDateStr);
                                            Date addEndDate = dateFormat.parse(addEndDateStr);
                                            maintenanceDAO.addMaintenance(addVehicleId, addStartDate, addEndDate);
                                        } catch (ParseException e) {
                                            System.out.println("🔴 Formato data non valido.");
                                        }
                                        break;
                                    case "2":
                                        System.out.println("Modifica manutenzione:");
                                        System.out.println("Inserisci l'ID della manutenzione da modificare:");
                                        Long editMaintenanceId = scan.nextLong();
                                        scan.nextLine();
                                        System.out.println("Inserisci la nuova data di inizio (dd-mm-yyyy):");
                                        String editNewStartDateStr = scan.nextLine();
                                        System.out.println("Inserisci la nuova data di fine (dd-mm-yyyy):");
                                        String editNewEndDateStr = scan.nextLine();
                                        try {
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                            Date editNewStartDate = dateFormat.parse(editNewStartDateStr);
                                            Date editNewEndDate = dateFormat.parse(editNewEndDateStr);
                                            maintenanceDAO.editMaintenance(editMaintenanceId, editNewStartDate, editNewEndDate);
                                        } catch (ParseException e) {
                                            System.out.println("🔴 Formato data non valido.");
                                        }
                                        break;
                                    case "3":
                                        System.out.println("Cancella manutenzione:");
                                        System.out.println("Inserisci l'ID della manutenzione da cancellare:");
                                        Long deleteMaintenanceId = scan.nextLong();
                                        scan.nextLine();
                                        maintenanceDAO.deleteMaintenance(deleteMaintenanceId);
                                        break;
                                    default:
                                        System.out.println("Azione non riconosciuta.");
                                        break;
                                    case "4":
                                        System.out.println("Visualizza manutenzioni per periodo:");
                                        System.out.println("Inserisci la data di inizio (dd-mm-yyyy):");
                                        String startPeriodStr = scan.nextLine();
                                        System.out.println("Inserisci la data di fine (dd-mm-yyyy):");
                                        String endPeriodStr = scan.nextLine();
                                        try {
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                            Date startPeriod = dateFormat.parse(startPeriodStr);
                                            Date endPeriod = dateFormat.parse(endPeriodStr);
                                            List<Maintenance> maintenanceList = maintenanceDAO.getMaintenanceByPeriod(startPeriod, endPeriod);
                                            if (!maintenanceList.isEmpty()) {
                                                System.out.println("Manutenzioni nel periodo specificato:");
                                                for (Maintenance maintenance : maintenanceList) {
                                                    System.out.println("ID: " + maintenance.getId() +
                                                            ", Veicolo: " + maintenance.getVehicle().getId() +
                                                            ", Start Date: " + maintenance.getStartDate() +
                                                            ", End Date: " + maintenance.getEndDate());
                                                }
                                            } else {
                                                System.out.println("Nessuna manutenzione nel periodo specificato.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("🔴 Formato data non valido.");
                                        }
                                        break;
                                }
                                break;
                            case "5":
                                System.out.println("Chiusura dell'applicazione.");
                                emf.close();
                                return;
                            default:
                                System.out.println("Scelta non riconosciuta.");
                                break;
                        }
                    }
                case "2":
                    while (true) {
                        System.out.println("Sei in possesso di un documento di viaggio? \n1. Possiedo un biglietto \n2. Possiedo una tessera/abbonamento \n3. Voglio acquistare/rinnovare un biglietto/tessera/abbonamento");
                        String scelta = scan.nextLine();
                        switch (scelta) {
                            case "1":
                                System.out.println("Scegli la tratta: ");
                                routeDAO.displayAllRoutes();
                                System.out.println("Inserisci l'id della tratta: ");
                                String idTratta = scan.nextLine();
                                switch (idTratta) {
                                    case "1":
                                        System.out.println("Piazzale Flaminio - Piazza Mancini");
                                        ticket1.setCheckedDate(LocalDate.now());
                                        ticketDAO.save(ticket1);
                                        break;
                                    case "2":
                                        System.out.println("Trastevere - Piazza Thorwaldsen");
                                        ticket1.setCheckedDate(LocalDate.now());
                                        ticketDAO.save(ticket1);
                                        break;
                                    case "3":
                                        System.out.println("Piazza dei Gerani - Giovanni Amendola");
                                        ticket1.setCheckedDate(LocalDate.now());
                                        ticketDAO.save(ticket1);
                                        break;
                                    case "4":
                                        System.out.println("Trastevere - Piazza Venezia");
                                        ticket1.setCheckedDate(LocalDate.now());
                                        ticketDAO.save(ticket1);
                                        break;
                                }
                                System.out.println("Buon viaggio!");
                                break;
                            case "2":
                                System.out.println("Scegli la tratta: ");
                                routeDAO.displayAllRoutes();
                                System.out.println("Inserisci l'id della tratta: ");
                                String idTratta2 = scan.nextLine();
                                switch (idTratta2) {
                                    case "1":
                                        System.out.println("Piazzale Flaminio - Piazza Mancini");
                                        break;
                                    case "2":
                                        System.out.println("Trastevere - Piazza Thorwaldsen");
                                        break;
                                    case "3":
                                        System.out.println("Piazza dei Gerani - Giovanni Amendola");
                                        break;
                                    case "4":
                                        System.out.println("Trastevere - Piazza Venezia");
                                        break;
                                }
                                System.out.println("Buon viaggio!");
                                break;
                            case "3":
                                System.out.println("Cosa vuoi fare? \n1. Acquistare un biglietto? \n2. Acquistare un abbonamento? \n3. Acquistare una tessera? \n4. Rinnovare un abbonamento?  \n5.  Rinnovare una tessera?");
                                String scelta3 = scan.nextLine();
                                switch (scelta3) {
                                    case "1":
                                        Ticket ticket2 = new Ticket(LocalDate.now(), null);
                                        break;
                                    case "2":
                                        Subscription subscription1 = new Subscription(LocalDate.now(), LocalDate.of(2024, 2, 20), SubscriptionPeriodicityDataTypes.MONTHLY);
                                        break;
                                    case "3":
                                        User user1 = new User("Pippo", "Pluto", LocalDate.of(2000, 1, 1));
                                        TravelCard travelCard1 = new TravelCard(LocalDate.now(), user1, TicketProviderDataTypes.RETAILER, LocalDate.of(2024, 12, 12));
                                }
                        }

                    }
            }
        }
    }
}
