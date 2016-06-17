# family-tree-miner
Our story is set in the planet of Lengaburu......in the distant, distant galaxy of Tara B. And our protagonists are King Shan, Queen Anga &amp; their family. King Shan is the emperor of Lengaburu and has been ruling the planet for the last 350 years (they have long lives in Lengaburu, you see!). Let’s write some code to get to know the family

```
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
 Draw Result [{f} Jaya, {f} Jnki, {f} Satya, {f} Lika] 1 daughters  each.

shell > Mother=Lavnya;Daughter=Rose
Lavnya is now related to Rose as Mother

shell > Mother=Lavnya;Daughter=Lilly
Lavnya is now related to Lilly as Mother

//count granddaughter who has most daughters
shell > #Daughter=Shan;
[{f} Lavnya] 3 daughters.

```
