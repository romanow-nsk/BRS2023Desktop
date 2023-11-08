/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

/**
 *
 * @author user
 */
public interface NoteViewListener {
    public void inInsertNote() throws Throwable;
    public void inRemoveNote() throws Throwable;
    public void inNoteHistory() throws Throwable;
    public void inInsertVariant(String var) throws Throwable;
    public void onError(Throwable ee);
}
