/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBItem;

/**
 *
 * @author user
 */
public interface EntryListener {
    public void onSelect(DBItem item, int mode);
}
