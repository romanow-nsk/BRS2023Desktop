/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.romanow.brs.javaview;

import me.romanow.brs.Values;

/**
 *
 * @author user
 */
public class CIUClient extends CloudClient{
    public CIUClient(){
        super();
        cloudIP=Values.dbNstuCloudIP;
        }
}
