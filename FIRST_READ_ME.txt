Hi, there

This programming challenge was really an interesting and enjoyable task.
Really appreciate Geektrust for their valuable efforts to fish out
promising and talented candidates. Your approaches are quite unique.

Thank you.

FOR THE IMPATIENT 
-----------------
java -jar final_sol.jar 

DEVELOPMENT PLATFORM
----------------------
- Intellij IDEA 14.1
- Java(TM) SE Runtime Environment (build 1.8.0_65-b17)

TEST CASE 
---------
There are few manual test cases in the test folder. Test#65 might be 
of interest to you guys. Junit etc weren't used as it would introduce
3rd part library dependency, which is not allowed according to the 
rule.

PROBLEM SET 04
--------------

Entire application has been made modular and extensible with clean
separation of concerns and less coupling.

I haven't deviated from the requirements however, modelled it in a
different style. The interaction with the family tree would be via
a shell interface. I'm not sure if this is what you guys have expe-
ted , however, this is how i found it useful for flexibility.

The shell have strict syntax checking, so. Lookout for missing ';'
and all.

Some deviations, from expected input are, the leading 's' in case
of some relation like Brother[s] , Sister[s] etc are not supported.
However, Brother, Sister etc will work. This is intentional, and was
required for loading classes via reflection.

* Relation aliases are case sensitive,

brother != Brother
motherinLaw != MotherInLaw
GrandChildren != Grandchildren

Overall, what ever has been asked in the specification has been imple-
mented, to the best of my ability. However, some expected i/p or o/p
style might be a little different though.

A sample usage of shell and its output is shown below

shell > help
 rlist
  \_list available relations
 Person = { member name } ; Relation = { a relation }
  \_find members in the Relation wrt Person
 Person = { member name } ; Relative = { member name }
  \_find Relations of Person wrt Relative
 Father/Mother = { member name } ; Daughter/Son = { new member name }
  \_create a son/daughter to an existing mother / father
 #Daughter = { member name } ;
  \_find max daughter count and member related, wrt member
 bye
  \_exit query session


shell > Person=Shan;Relation=Children
[{f} Satya, {m} Ish, {m} Chit, {m} Vich]

shell > rlist
( Case Sensitive ) : [MaternalUncle, GreatGrandMother, GrandDaugther,
CousinSister, MaternalAunt, FatherInLaw, Mother, GreatGrandFather,
Brother, Wife, Children, Sister, Nephew, HalfBrother, ParentInLaw,
GrandChildren, GrandParent, Daughter, HalfSister, Cousin, GrandFather,
SonInLaw, CousinBrother, Neice, MotherInLaw, DaughterInLaw, Husband,
BrotherInLaw, Son, GreatGrandParent, GrandMother, SisterInLaw, GrandSon,
PaternalUncle, Father, PaternalAunt]

shell > Person=Shan;Relation=Wife
[{f} Anga]

shell > Person=Shan;Relation=GrandChildren
[{m} Drita, {m} Vrita, {m} Vila, {f} Chika, {f} Satvy, {m} Savya, {m} Saayan]

shell > Person=Lavnya;Relative=Shan
[GreatGrandFather]

shell > Person=Kriya;Relative=Saayan
[PaternalUncle]

shell > Mother=Lavnya;Daughter=Vanya
Lavnya is now related to Vanya as Mother

shell > Person=Jnki;Relative=Vanya
[GrandChildren, GrandDaugther]

shell > Mother=Lavnya;Son=Reeva
Lavnya is now related to Reeva as Mother

shell > Person=Jnki;Relative=Reeva
[GrandChildren, GrandSon]

shell > Person=Jnki;Relation=GrandChildren
[{f} Vanya, {m} Reeva]

//count granddaughter who has most daughters
//a draw result
shell > #Daughter=Shan;
 Draw Result [{f} Satya, {m} Vich, {m} Drita, {m} Vila, {f} Lavnya] 1 daughters  each.

shell > Mother=Lavnya;Daughter=Rose
Lavnya is now related to Rose as Mother

shell > Mother=Lavnya;Daughter=Lilly
Lavnya is now related to Lilly as Mother

//count granddaughter who has most daughters
shell > #Daughter=Shan;
[{f} Lavnya] 3 daughters.

---
Happy Hacking
- Althaf
