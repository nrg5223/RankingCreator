# Ranking Creator

## Nicholas Grosskopf

Personal project - started 1/21/2022.

### Inspiration
>   My desire to rank all songs by The Weeknd, my favorite artist.  It's hard to
compare many items manually, and there could be other issues.  There could  
be a rock-paper-scissors scenario where song A beats song B, song B beats
song C, and song C beats song A.  Comparing every song to every other song
gives the best overall representation of my opinion-based ranking.

### Purpose
>   Create an application used for fairly ranking a list of items based on user
opinion.

### Features
> - GUI
> - Pattern analysis in rankings based on other factors
> - Add new items to existing rankings
> - Re-rank selected items in existing rankings
> - Add new characteristics to existing lists of items 
> - Adding items to tiers, ranking within those tiers, then ranking those tiers
> - Automatically creating a backup file for each NEW file created.  The backup 
>   folder will be written to, but never deleted (or overwritten, I think)


### PTUI Workflow Plan
> Home Screen
>> Tasks: 
>> - input list of items (with extra details if desired)
>> - choose from (and view) existing lists of data (.txt files)
>>      - rank this list
>>          - to: ranking screen
>>      - add new item(s) to this list
>>          - to: ranking screen
>>      - remove from this list (note: this requires updating the sorted list)
>>      - re-rank selected items
>>          - to: ranking screen
>> - go to result screen
>>      - to: result screen
>> - quit
>>      - to: (closes application)
> 
> Ranking Screen
>> Tasks:
>> -  see two items, choose between them, see next two items (until done)
>>      - to: result screen
> 
> Result Screen
>> Tasks:
>> - select and view whole lists of results (.txt files)
>>      - filter results (ex. only show MDM songs; only show 2015 songs; only
          show features)
>>      - select and view rankings of characteristics of the ranked items (ex. 
          rankings of the projects; rankings of song length ranges)
>> - go to home screen
>>      - to: home screen

### Re-ranking plan
>
> 1. Add new items to data file
> 2. Create new combination set (combos w/ new items)
> 3. Get user votes for new combinations
> 4. Write results to result file
> 

### Known Bugs
> None.
>
> Last checked on: 1/23/2022
