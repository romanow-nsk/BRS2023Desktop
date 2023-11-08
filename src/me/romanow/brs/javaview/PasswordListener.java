/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.romanow.brs.javaview;

/**
 *
 * @author romanow
 */
public interface PasswordListener {
    public void onSelect(String value, boolean admin);
    public void onError(String value);
}
