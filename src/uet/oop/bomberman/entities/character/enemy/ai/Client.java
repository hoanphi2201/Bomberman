/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.character.enemy.ai;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author phixuanhoan
 */
public class Client extends AI{
    // Server Socket
    Scanner sc1;
    
   

    public Client(Scanner sc1) {
        this.sc1 = sc1;
       
    }
    @Override
    public int calculateDirection() {
        
        return sc1.nextInt();
    }

}
