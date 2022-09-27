/* SimpleServer.java
   This is the server program
   Author: Zukhanye Anele Mene
   Date: 15 September 2022 */

package za.ac.mycput.libraryserver;

import java.net.*;
import java.io.*;
import za.ac.mycput.libraryserver.domain.Book;

public class LibraryServer {
    public static void main(String args[]) {
        ServerSocket s = null;

        // Register your service on port 5432
        try {
            s = new ServerSocket(5432);
        } 
        catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            System.exit(0);
        }

        // Run the listen/accept loop forever
        while (true) {
            try {
                // Wait here and listen for a connection
                System.out.println("Server running...listening for a connection...");
                Socket s1 = s.accept();

                // Get output stream associated with the socket
                System.out.println("Connection established");
                ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
                 

                // Send your message!
                System.out.println("Sending data...");
                Book b = new Book("01", "7-27-527254-5", "Dora", "2nd rev, edition", "3 star", "2002-02-08", "001");
                output.writeObject(b);
                output.flush();

                // Close the connection, but not the server socket
                output.close();
                s1.close();
                System.out.println("Connection closed.");
            } 
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
