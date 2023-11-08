/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBProfile;

/**
 *
 * @author user
 */
public interface ProfileListener {
    public void onSelect(DBProfile item);
}
