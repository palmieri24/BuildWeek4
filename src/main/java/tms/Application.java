package tms;

import tms.transport.dao.MaintenanceDAO;
import tms.transport.dao.RouteDAO;
import tms.transport.dao.StopDAO;
import tms.transport.dao.VehicleDAO;
import tms.transport.entities.Route;
import tms.transport.entities.Vehicle;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {

  public static void main(String[] args) {
    System.out.println("⚪ Avvio Entity Manager Factory (emf).");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("tms");
    System.out.println("⚪ Avvio Entity Manager tramite Entity Manager Factory.");
    Scanner scan = new Scanner(System.in);
    StopDAO stopDAO = new StopDAO(emf);
    VehicleDAO vehicleDAO = new VehicleDAO(emf);
    RouteDAO routeDAO = new RouteDAO(emf);
    MaintenanceDAO maintenanceDAO = new MaintenanceDAO(emf);
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
          System.out.println("Cosa vuoi fare con le manutenzioni?\n1. Aggiungi manutenzione\n2. Modifica manutenzione\n3. Cancella manutenzione");
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
  }
}