<form>
    Nom : <input type="text" name="nomCpt" value="${membre.getNom()}"/><br>
    Prenom : <input type="text" name="prenomCpt" value="${membre.getPrenom()}"/><br>
    Type : 
    <select name='typeCpt'> 
        <option value="Part" ${selectPart} >Particulier</option>
        <option value="Pro" ${selectPro} >Professionnel</option>
    </select><br>
    <%--Type : <input type="text" name="typeCpt" value="${membre.getType()}"/>--%>
    Date de naissance : <input type="text" name="dateNaissCpt" value="${membre.getDateNaissance()}"/><br>
    E-mail : <input type="text" name="mail" value="${membre.getMail()}"/><br>
    Telephone : <input type="text" name="tel" value="${membre.getTel()}"/><br>
    Telephone portable : <input type="text" name="port" value="${membre.getPort()}"/><br>
    Mot de passe : <input type="text" name="mdp" value="${membre.getMdp()}"/><br>

    <input type="submit" name="suppr" value="Supprimer"/>
    <input type="submit" name="modif" value="Modifier"/>
</form>