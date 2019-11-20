# springBatch
Le paiement par carte bancaire n'induit pas le débit immédiat du compte. 
La transaction ne sera validée qu'au début du mois suivant. Ainsi le compte sera debité au début du mois suivant.

Au moment du paiement par carte, la transaction est journalisée dans un fichier csv.
Le fichier de journalisation csv contient les informations suivantes : 
idTransaction,idCompte,montant,dateTransaction

dateTransaction est la date de transaction et non pas la date de débit du compte. 

On est donc amené à écrire un batch qui permet, au début de chaque mois, de lire à partir du fichier csv des transactions, puis enregistrer les transactions dans la base de données et débiter les comptes.
DateDebit est la date ou le compte à été Débité. 

Sachant que les entités Transaction et Compte sont définit comme suit : Transaction(idTransaction,montant,dateTransaction,dateDebit,idCompte)
et Compte(idCompte,solde).
