# Process Record (approximate)
###1/21
> 2 hours
>>   - 2 hours coding
>
> Tasks
>>  - Created:
>>      - MakeRankings
>>      - Comparer
>>      - Ranker (currently called Result)
>>      - Song
>>      - TimeConverter

###1/23
> 11 hours
>>  - 5 hours coding
>>  - 3 hours designing
>>  - 2 hours debugging
>>  - 1 hour refactoring
>
> Tasks
>>  - Refactored:
>>      - Result (was called Ranker)
>>      - Rankable/Song (moved shared logic to Rankable)
>>  - Designed:
>>      - PTUI Workflow
>>  - Created:
>>      - README.md file
>>      - RankingsGUI
>>      - RankingsClientData
>>      - RankingsPTUI
>>      - Observer

###1/24
> 2 hours
>> - 1.5 hours coding
>> - 0.5 hours designing
>
> Tasks
>> - Refactored:
>>      - RankingsPTUIHome (was RankingsPTUI)
>> - Created:
>>      - Data
>>      - RankingsPTUIResults

###1/25
> 6 hours
>>  - 4 hours coding
>>  - 2 hours refactoring
> 
> Tasks
>>  - Refactored:
>>      - RankingsPTUIHome (renamed commands, added functionality to create new files)
>>      - Data (took file-related logic from RankingsPTUIHome)
 
###1/26
> 3 hours
>> - 2 hours refactoring
>> - 1 hour coding
> 
> Tasks
>> - Refactored:
>>      - Data (files rewritten as data files; they show points & isRanked)
> 

###Current Tasks
> - (In Data.getSetFromFile()) figure out how the program decides which lines
> (which rankables) to store, and how.  This affect re-ranking existing data and
> adding new file, which is then ranked (but only the new stuff).  Currently, if
> new data is added to a file, only the new data is ranked, and the file is
> rewritten and re-ranked with only the new data; the old data is lost.
> 
> 
> - Refactor Rankable.  My idea is Rankable is not abstract anymore.  Nothing
> implements it.  It has a parameter Object[] argsArray, which is an array
> of variable size, with variable element types.  This allows the system to
> instantiate rankables with any arguments, and it all depends on the inputs 
> from the user.  The important requirement is that the user can input a 
> variable number of arguments, and any "type" of rankable at any time; the
> system needs to have the additional data the user wants to be stored.
