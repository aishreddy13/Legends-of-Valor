# Legends-of-Valor
Legends of Valor

Legends Of Valor Game

Project submitted by 
Harshitha Tumkur Kailasa Murthy (harshutk@bu.edu) - U00683580  and 
Aishwarya Reddy Lachangar(lareddy@bu.edu) - U62138442

Bonus
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
We have used sound and colors as the beautification factors.
We have used Factory Method as a design pattern implementation

Classes we have Used and their functionalities are as below :
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

1. Grid Class consists of methods to help with Board functionality of any game, implements the interface IGrid
2. Grid Interface consists of methods to help with Board functionality of any game
3. IValorMap Interface consists of methods to help with Heroes, Monsters and movements for each character functionality in Legends game
4. ValorMap Class implements IValor Map and has functions with respect to Legends of Valor game Map
5. Class Hero implements the Interface IHero, this class consists of methods to calculate Heroes features and battle conditions
6. Interface IHero has methods to calculate Heroes features and battle conditions
7. Class Paladin is to create the Paladin type of Heroes
8. Class Sorcerer is to create the Sorcerer type of Heroes
9. Class to create the Warrior type of Heroes
10. Class to accomodate Colors features for different Classes
11. Class Armor consists of getters and setters for Armour, implements IArmor, Implements Interface Spell
12. Class FireSpell consists of getters and setters, different Firespell functionalities as methods to call at various classes , Implements Interface Spell
13. Interface Armor consists of getters and setters for Armour
14. Class IceSpell consists of getters and setters, different IceSpell functionalities as methods to call at various classes, Implements Interface Spell
15. Interface IItem consists of getters and setters to call at various classes
16. Interface Ipotion consists of getters and setters, various Potion functionalities as methods to call at various classes
17. Interface Spell consists of getters and setters, different Spell functionalities as methods to call at various classes
18. Class Item implements the Interface IItem and holds the methods to help the Items functionalities in market.
19. Interface IWeapon consists of getters and setters methods to call at various classes
20. Class LightningSpell implements the Interface ISpell and holds the methods to help the Items functionalities of using this spell.
21. Class Potion implements the Interface IPotion and holds the methods to help the Items functionalities of potion.
22. Class Spell implements the Interface ISpell and holds the methods to help the Items functionalities of spell.
23. Class Weapon implements the Interface IWeapon and holds the methods to help the Items functionalities of weapons.
24. Interface Imarket consists of various Market functionalities as methods to call at various classes
25. Class Market implements the Interface Imarket and holds the methods to help the Items functionalities of Market.
26. Class Dragon implements the Class Monster and holds the constructor for this monster type.
27. Class Exoskeleton implements the Class Monster and holds the constructor for this monster type.
28. Interface IMonster consists of getters a, setters and methods for monster functionalities
29. Class Monster implements the Interface IMonster and holds the getters, setters and methods to calculate damage of various monster types.
30. MonsterFactory is a design pattern used to create Monsters
31. Class Spirit implements the Class Monster and holds the constructor for this monster type.
32. Class MainValor is the Legends of Valor driver class from where the game should be executed.

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

We tried to see the Object structure of both codes. We decided to pick the features that were common from previous assignment to be incorporated into this. We built on top of that by coding for new Board and Heroes and Monsters functionalities.

We planned to pick the design Board and its connecting structures from Harshitha's Code as it was extendible.

We planned to pick the Heroes and Monster structure from Aishwarya's code as it had individual classes for each.

We built the new features on top this basic idea.
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
Steps for execution :
------------------------------------------------------------------------------------------------
Navigate to directory and execute using java terminal commands.
javac -d bin src/infos/*.java src/item/*.java src/heroes/*.java src/grid/*.java src/market/*.java src/monster/*.java src/*.java
java -cp bin MainValor
The "MainValor" Class is the driver Class to be executed.

Keep the volume high to listen to the background music.

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
Output :
------------------------------------------------------------------------------------------------

        **              ***********         ****       ***********      **         **       **** **                 
        **              **                **           **               ****       **       **     **              
        **              **               **            **               **  **     **       **      **         
        **              ***********      **            ***********      **    **   **       **       **        
        **              **               **     **     **               **      ** **       **       **      
        **              **                **     **    **               **       ****       **      **       
        ************    ************        *****      ************     **         **       ****  **          
______________________________________________________________________________________________________________
 *******************************************************WELCOME TO LEGENDS OF VALOR GAME***************************************************************************************** 
 +"""""+          Legends Of Valor game. Heros and Monsters are two kinds of characters in the game
[| o o |]          This Layout Map will have the number of Heros selected by you in the selected lanes of the board
 |  ^  |           There will various kinds of spaces in this game, Cave, Koulu, Bush, Plain and Nexus
 | '-' |           The winner of the game is decided based on the who reaches the Nexus of other party first
 +-----+ 
 +"""""+               EXCITED TO PLAY, GAME STARTS IN 3.......2.......1......
[| * * |]
 |  ^  | 
 | 'O' | 
 +-----+ 
************************************************************************************************************************************************************************
 INACCESSIBLE SPOTS
 PLAIN
 KOULU
 CAVE
 BUSH
 NEXUS
************************************************************************************************************************************************************************
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

Welcome to Legends of Valor!
Your legend awaits...
What type of hero would you like?
Warrior = 1, Sorcerer = 2, Paladin = 3
1
YOUR CHOICE IS 1
Please select a Warrior with the corresponding number

|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|1-Gaerdal_Ironhand       |       100 |       700 |       500 |       600 |               1354 |                  7 |
|2-Sehanine_Monnbow       |       600 |       700 |       800 |       500 |               2500 |                  8 |
|3-Muamman_Duathall       |       300 |       900 |       500 |       750 |               2546 |                  6 |
|4-Flandal_Steelskin      |       200 |       750 |       650 |       700 |               2500 |                  7 |
|5-Undefeated_Yoj         |       400 |       800 |       400 |       700 |               2500 |                  7 |
|6-Eunoia_Cyn             |       400 |       700 |       800 |       600 |               2500 |                  6 |

1
You have chosen:
|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|1-Gaerdal_Ironhand       |       100 |       700 |       500 |       600 |               1354 |                  7 |
Which lane of map hero will be ?
Type 1 for Top Lane
Type 2 for Mid Lane
Type 3 for Bot Lane
2
YOUR CHOICE IS Mid Lane
What type of hero would you like?
Warrior = 1, Sorcerer = 2, Paladin = 3
2
YOUR CHOICE IS 2
Please select a Sorcerer with the corresponding number

|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|1-Rillifane_Rallathil    |      1300 |       750 |       450 |       500 |               2500 |                  9 |
|2-Segojan_Earthcaller    |       900 |       800 |       500 |       650 |               2500 |                  5 |
|3-Reign_Havoc            |       800 |       800 |       800 |       800 |               2500 |                  8 |
|4-Reverie_Ashels         |       900 |       800 |       700 |       400 |               2500 |                  7 |
|5-Kalabar                |       800 |       850 |       400 |       600 |               2500 |                  6 |
|6-Skye_Soar              |      1000 |       700 |       400 |       500 |               2500 |                  5 |

3
You have chosen:
|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|3-Reign_Havoc            |       800 |       800 |       800 |       800 |               2500 |                  8 |
Which lane of map hero will be ?
Type 1 for Top Lane
Type 3 for Bot Lane
3
YOUR CHOICE IS Bot Lane
What type of hero would you like?
Warrior = 1, Sorcerer = 2, Paladin = 3
3
YOUR CHOICE IS 3
Please select a Paladin with the corresponding number

|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|1-Parzival               |       300 |       750 |       650 |       700 |               2500 |                  7 |
|2-Sehanine_Moonbow       |       300 |       750 |       700 |       700 |               2500 |                  7 |
|3-Skoraeus_Stonebones    |       250 |       650 |       600 |       350 |               2500 |                  4 |
|4-Garl_Glittergold       |       100 |       600 |       500 |       400 |               2500 |                  5 |
|5-Amaryllis_Astra        |       500 |       500 |       500 |       500 |               2500 |                  5 |
|6-Caliber_Heist          |       400 |       400 |       400 |       400 |               2500 |                  8 |

5
You have chosen:
|Name                     |      Mana |  Strength |   Agility | Dexterity |     Starting Money |Starting Experience |
|5-Amaryllis_Astra        |       500 |       500 |       500 |       500 |               2500 |                  5 |
Which lane of map hero will be ?
Type 1 for Top Lane
1
YOUR CHOICE IS Top Lane
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N


-------------------------
Type P to start round.
Type M to enter Market.
Type I to show info.
Type Q to quit.
p
-------------------------
You're in round number: 1

Hero's turn: Hero{name='Amaryllis_Astra', level=1, xp=5, HP=100.0, MP=500.0, strength=500.0, dexterity=500.0, agility=500.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Gaerdal_Ironhand', level=1, xp=7, HP=100.0, MP=100.0, strength=700.0, dexterity=600.0, agility=500.0, gold=1354.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Reign_Havoc', level=1, xp=8, HP=100.0, MP=800.0, strength=800.0, dexterity=800.0, agility=800.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N


-------------------------
Type P to start round.
Type I to show info.
Type Q to quit.
p
-------------------------
You're in round number: 2

Hero's turn: Hero{name='Amaryllis_Astra', level=1, xp=5, HP=110.00000000000001, MP=550.0, strength=500.0, dexterity=550.0, agility=500.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Gaerdal_Ironhand', level=1, xp=7, HP=110.00000000000001, MP=110.00000000000001, strength=700.0, dexterity=600.0, agility=500.0, gold=1354.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Reign_Havoc', level=1, xp=8, HP=110.00000000000001, MP=880.0000000000001, strength=800.0, dexterity=880.0000000000001, agility=800.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N


-------------------------
Type P to start round.
Type I to show info.
Type Q to quit.
p
-------------------------
You're in round number: 3

Hero's turn: Hero{name='Amaryllis_Astra', level=1, xp=5, HP=121.00000000000003, MP=605.0, strength=500.0, dexterity=550.0, agility=500.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Gaerdal_Ironhand', level=1, xp=7, HP=121.00000000000003, MP=121.00000000000003, strength=700.0, dexterity=600.0, agility=500.0, gold=1354.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n

Hero's turn: Hero{name='Reign_Havoc', level=1, xp=8, HP=121.00000000000003, MP=968.0000000000002, strength=800.0, dexterity=800.0, agility=880.0000000000001, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
n
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N


-------------------------
Type P to start round.
Type I to show info.
Type Q to quit.
p
-------------------------
You're in round number: 4

Hero's turn: Hero{name='Amaryllis_Astra', level=1, xp=5, HP=133.10000000000005, MP=665.5, strength=550.0, dexterity=499.99999999999994, agility=500.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type C to attack a monster.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
c

Choose a monster to attack
(1)Monster{name='BigBad-Wolf', level=1, HP=100.0, damage=150.0, defense=250.0, ability=15.0}

Type number of monster to attack
1
Amaryllis_Astra attacked BigBad-Wolf for 7.5 damage!

Hero's turn: Hero{name='Gaerdal_Ironhand', level=1, xp=7, HP=133.10000000000005, MP=133.10000000000005, strength=700.0, dexterity=600.0, agility=500.0, gold=1354.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type C to attack a monster.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
c

Choose a monster to attack
(1)Monster{name='Blinky', level=1, HP=100.0, damage=450.0, defense=350.0, ability=35.0}

Type number of monster to attack
1
Gaerdal_Ironhand attacked Blinky for 7.0 damage!

Hero's turn: Hero{name='Reign_Havoc', level=1, xp=8, HP=133.10000000000005, MP=1064.8000000000004, strength=800.0, dexterity=880.0000000000001, agility=800.0, gold=2500.0, weapon=null, armor=null}

Type N to move North.
Type E to move East.
Type S to move South.
Type C to attack a monster.
Type P to use a Potion.
Type L to use a Spell.
Type T to teleport.
Type H to manage inventory.
Type R to recall.
Type I to show info.
c

Choose a monster to attack
(1)Monster{name='BigBad-Wolf', level=1, HP=100.0, damage=150.0, defense=250.0, ability=15.0}

Type number of monster to attack
1
Reign_Havoc attacked BigBad-Wolf for 20.0 damage!
BigBad-Wolf attacked Amaryllis_Astra for 22.5 damage!
Blinky attacked Gaerdal_Ironhand for 67.5 damage!
BigBad-Wolf attacked Reign_Havoc for 22.5 damage!
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  P - P - P  I - I - I  P - P - P  B - B - B  I - I - I  C - C - C  K - K - K

C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |    M1 |  | X X X |  |       |  |    M2 |  | X X X |  |       |  |    M3 |
P - P - P  K - K - K  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B
| H1    |  |       |  | X X X |  | H2    |  |       |  | X X X |  | H3    |  |       |
K - K - K  P - P - P  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  B - B - B

B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  P - P - P  I - I - I  P - P - P  C - C - C  I - I - I  C - C - C  K - K - K

B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  K - K - K

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N


-------------------------
Type P to start round.
Type I to show info.
Type Q to quit.
