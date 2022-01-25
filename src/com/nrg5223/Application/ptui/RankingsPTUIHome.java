package com.nrg5223.Application.ptui;

import com.nrg5223.Application.Data.Data;
import com.nrg5223.Application.Observer;
import com.nrg5223.Application.gui.RankingsClientData;
import com.nrg5223.RankingOperation.MakeRankings;
import com.nrg5223.RankingOperation.Result;

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
            System.out.print( "Application command: " );
            String line = in.nextLine();
            String[] command = line.split( "\\s+" );
            if (command.length > 0) {
                if (command[0].startsWith("q")) {
                    break;
                } else if (command[0].startsWith("r")) {
                    if (command.length != 3) {
                        Data.showFilesIn(Data.DATA_DIR);
                    }
                    else {
                        String fileToRead = Data.createDataFileName(command[1]);
                        String fileToWriteTo = Data.createResultsFileName(command[1]);
                        String rankableType = command[2];
                        MakeRankings.main(new String[]{fileToRead, fileToWriteTo, rankableType});
                    }
                } else if (command[0].startsWith("v")) {
                    // TODO: need to refactor this and use RankingsPTUIResults
                    //  for all result-viewing functionality (this will get more
                    //  complicated in the future with data patterns and
                    //  filtering)
                    if (command.length != 2) {
                        Data.showFilesIn(Data.RESULTS_DIR);
                    }
                    else {
                        String fileToRead = Data.createResultsFileName(command[1]);
                        System.out.println(Data.contentOf(fileToRead));
                    }
                } else {
                    displayHelp();
                }
            }
            else {
                displayHelp();
            }
        }
        System.out.println("Application closed.");
    }

    /**
     * Display a message that shows the user the controls
     */
    private void displayHelp() {
        System.out.println("q(uit)                      -- quit the application");
        System.out.println("r(ank) filename item-type   -- rank the data in a file");
//        System.out.println("w(rite) filename            -- write data to a new file");
//        System.out.println("a(dd) filename              -- add new items to a file");
//        System.out.println("d(elete) filename           -- delete items from a file");
//        System.out.println("u(pdate) filename           -- update rankings in a file");
        System.out.println("v(iew)                        -- view the results");
        System.out.println();
    }

    @Override
    public void update(Result result, RankingsClientData rankingsClientData) {
        // TODO
    }
}
