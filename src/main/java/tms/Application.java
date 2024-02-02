package tms;

import com.github.javafaker.Faker;
import tms.transport.dao.*;
import tms.transport.entities.*;
import tms.transport.enums.SubscriptionPeriodicityDataTypes;
import tms.transport.enums.TicketProviderDataTypes;
import tms.transport.dao.RouteDAO;
import tms.transport.dao.StopDAO;
import tms.transport.dao.VehicleDAO;
import tms.transport.entities.Route;
import tms.transport.entities.Vehicle;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
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

    while (true) {
      System.out.println("Cosa vuoi fare?\n1. Gestisci fermate\n2. Gestisci veicoli\n3. Gestisci rotte\n4. Esci");
      String scelta = scan.nextLine();

        // 👤 Aggiungi utente
//       User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(18, 99));
//        userDAO.save(user);

//        TicketDAO td = new TicketDAO(em);
  //      SubscriptionDAO sd = new SubscriptionDAO(em);
  //      Subscription sub = new Subscription(LocalDate.of(2023, 5, 5), null, SubscriptionPeriodicityDataTypes.MONTHLY);
////        sd.save(sub);
//
//        System.out.println("FIND TICKETS BY DATE");
//        List<TransportDocument> yearTicket = td.findTicketsByDates(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearTicket.forEach(System.out::println);
//
//        System.out.println("FIND SUBSCRIPTIONS BY DATE");
//        List<TransportDocument> yearSub = sd.findSubscriptionsByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearSub.forEach(System.out::println);

        //RINNOVA TESSERA
       /* TravelCardDAO tc = new TravelCardDAO(em);
        LocalDate emissionDate = LocalDate.of(2020, 1, 8);
        LocalDate expireDate = LocalDate.of(2021,1,8);
        TravelCard travelCard = new TravelCard(emissionDate, user, TicketProviderDataTypes.MACHINE, expireDate);
        tc.save(travelCard);
        tc.renewTravelCard(travelCard.getId(), emissionDate, expireDate);
        System.out.println("Tessera" + travelCard.getId() + "rinnovata con successo!"); */

        //VERIFICA VALIDITÀ ABBONAMENTO
       /* boolean subValidity = sd.isSubscriptionValid(sub.getId());
        if (subValidity == true){
            System.out.println("Abbonamento valido!");
        } else {
            System.out.println("Abbonamento non valido!");
        }*/




        System.out.println("🔴 Chiusura dell'EntityManagerFactory & EntityManager alla fine dell'applicazione");
        emf.close();
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
          System.out.println("Gestione delle rotte:\n1. Aggiungi rotta\n2. Modifica rotta\n3. Cancella rotta\n4. Assegna mezzo\n5. Ottieni il tempo di percorrenza di una delle rotte");
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