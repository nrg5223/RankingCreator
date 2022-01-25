package com.nrg5223.Application.ptui;

import com.nrg5223.Data.Data;
import com.nrg5223.Application.Observer;
import com.nrg5223.Application.gui.RankingsClientData;
import com.nrg5223.RankingOperation.MakeRankings;
import com.nrg5223.RankingOperation.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RankingsPTUIHome implements Observer<Result, RankingsClientData> {

    /*
        Idea: have a main() method for each of the three "pages" in the
        application
            - HomePTUI (may come from this class or MakeRankings)
            - RankingPTUI (may come from comparer)
            - ResultsPTUI (may come from Result)
     */
    public static void main(String[] args) throws IOException {
        RankingsPTUIHome ptui = new RankingsPTUIHome();
        ptui.run();
    }

    private void run() throws IOException {
        Scanner in = new Scanner( System.in );
        displayHelp();
        for ( ; ; ) {
            System.out.println();
            System.out.print( "Application command: " );
            String line = in.nextLine();
            String[] command = line.split( "\\s+" );
            if (command.length > 0) {
                if (command[0].startsWith("q")) {
                    break;

                } else if (command[0].startsWith("df")) {
                    Data.showFilesIn(Data.DATA_DIR);

                } else if (command[0].startsWith("rf")) {
                    Data.showFilesIn(Data.RESULTS_DIR);

                } else if (command.length == 2 && command[0].startsWith("r")) {
                    try {
                        String fileToRead = Data.createDataFileName(command[1]);
                        String fileToWriteTo = Data.createResultsFileName(command[1]);
                        if (Data.dirContainsFile(Data.DATA_DIR, command[1])) {
                            MakeRankings.main(new String[]{fileToRead, fileToWriteTo});
                        } else {
                            Data.showFilesIn(Data.DATA_DIR);
                        }
                    } catch (NullPointerException e) {
                        String message = "Unable to run voting operation. " +
                                "Data file is empty.";
                        System.out.println(message);
                    }

                } else if (command.length == 2 && command[0].startsWith("n")) {
                    String newFileName = command[1];
                    if (Data.dirContainsFile(Data.DATA_DIR, newFileName)) {
                        String message = newFileName + " already exists in the " +
                                "data directory.";
                        System.out.println(message);
                    } else {
                        Data.createAndWriteNewFile(in, newFileName);
                    }

                } else if (command.length == 2 && command[0].startsWith("a")) {
                    if (!Data.dirContainsFile(Data.DATA_DIR, command[1])) {
                        Data.showFilesIn(Data.DATA_DIR);
                    } else {
                        Data.addDataTo(in, command[1]);
                    }

                // TODO: need to refactor this and use RankingsPTUIResults
                //  for all result-viewing functionality (this will get more
                //  complicated in the future with data patterns and
                //  filtering)
                } else if (command.length == 2 && command[0].startsWith("v")) {
                    if (Data.dirContainsFile(Data.RESULTS_DIR, command[1])) {
                        String chosenResultsFile = Data.createResultsFileName(command[1]);
                        System.out.println(Data.contentOf(chosenResultsFile));
                    } else {
                        Data.showFilesIn(Data.RESULTS_DIR);
                    }

                // TODO: remove this section after testing delete().  It is
                //  just here to test the function
                } else if (command.length == 2 && command[0].startsWith("d")) {
                    String fileToDelete = Data.createDataFileName(command[1]);
                    Data.delete(command[1]);
                } else {
                    displayHelp();
                }
            } else {
                displayHelp();
            }
        }
        System.out.println("Application closed.");
    }

    /**
     * Display a message that shows the user the controls
     */
    private void displayHelp() {
        System.out.println("q(uit)                     -- quit the application");
        System.out.println("df(iles)                   -- see the data files in the system" );
        System.out.println("rf(iles)                   -- see the results files in the system" );
        System.out.println("r(ank) filename            -- rank the data in a file");
        System.out.println("n(ew) filename             -- write data to a new file");
        System.out.println("a(dd to) filename          -- add new items to a file");
//        System.out.println("d(elete from) filename       -- delete items from a file");
//        System.out.println("u(pdate) filename            -- update rankings in a file");
        System.out.println("v(iew) filename            -- view the results in a file");
    }

    @Override
    public void update(Result result, RankingsClientData rankingsClientData) {
        // TODO
    }
}
