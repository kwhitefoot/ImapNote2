package com.Pau.ImapNotes2.Miscs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.Pau.ImapNotes2.Data.Db;
import com.Pau.ImapNotes2.Data.OneNote;
import com.Pau.ImapNotes2.Listactivity;
import com.Pau.ImapNotes2.NotesListAdapter;

import java.util.ArrayList;

//import com.Pau.ImapNotes2.Data.NotesDb;

public class SyncThread extends AsyncTask<Object, Void, Boolean> {
    private final ProgressDialog progressDialog;
    private final NotesListAdapter adapter;
    private final ArrayList<OneNote> notesList;
    /**
     * SQLite database that holds status information about the notes.
     */
    // TODO: NoteDb should probably never be null.
    @NonNull
    private final Db storedNotes;
    // --Commented out by Inspection (11/26/16 11:48 PM):boolean bool_to_return;
// --Commented out by Inspection START (11/26/16 11:48 PM):
//    @NonNull
//    ImapNotes2Result res = new ImapNotes2Result();
// --Commented out by Inspection STOP (11/26/16 11:48 PM)
    @SuppressWarnings("unused")
    private static final String TAG = "SyncThread";

    // TODO: remove unused arguments.
    public SyncThread(ArrayList<OneNote> noteList,
                      NotesListAdapter listToView,
                      ProgressDialog loadingDialog,
                      @Nullable Db storedNotes,
                      Context applicationContext) {
        //this.imapFolder = imapFolder;
        //this.imapNotes2Account = imapNotes2Account;
        this.notesList = noteList;
        this.adapter = listToView;
        this.progressDialog = loadingDialog;
        //this.storedNotes = storedNotes;
        this.storedNotes = (storedNotes == null) ? new Db(applicationContext) : storedNotes;

    }

    // Do not pass arguments via execute; the object is never reused so it is quite safe to pass
    // the arguments in the constructor.
    @NonNull
    @Override
    protected Boolean doInBackground(Object... stuffs) {
        /*String username = null;
        String password = null;
        String server = null;
        String portnum = null;
        String security = null;
        String usesticky = null;
*/
         /*       this.adapter = ((NotesListAdapter) stuffs[3]);
        this.notesList = ((ArrayList<OneNote>) stuffs[2]);
        this.storedNotes = ((NotesDb) stuffs[5]);
        this.ctx = (Context) stuffs[6];
 */
        //username = ((ImapNotes2Account) stuffs[1]).GetUsername();
        //password = ((ImapNotes2Account) stuffs[1]).GetPassword();
        //server = ((ImapNotes2Account) stuffs[1]).GetServer();
        //portnum = ((ImapNotes2Account) stuffs[1]).GetPortnum();
        //security = ((ImapNotes2Account) stuffs[1]).GetSecurity();
        //usesticky = ((ImapNotes2Account) stuffs[1]).GetUsesticky();


        storedNotes.OpenDb();
        storedNotes.notes.GetStoredNotes(this.notesList, Listactivity.imapNotes2Account.accountName);
        storedNotes.CloseDb();
        progressDialog.dismiss();
        return true;
    }

    protected void onPostExecute(Boolean result) {
        if (result) {
            this.adapter.notifyDataSetChanged();
        }
    }
}
