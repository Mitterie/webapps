<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Se connecter</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">

    <style>
        body {
            background-image: url("img/backgroud.jpg");
            background-size: cover;
            height: 100vh;
            margin: 0;
            user-select: none;
            font-family: Arial, Helvetica, sans-serif;
        }

        body > div {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            border: 0.1vh solid white;
            border-radius: 2vh;
            padding: 2vh;
            text-align: center;
            backdrop-filter: blur(1vh);
            box-shadow: 0 0 2.5vh 1vh rgba(0,0,0,0.1);
        }

        body > div > h1 {
            font-size: 5vh;
            color: white;
            margin: 2vh 3vh;
        }
        
        body > div > a {
            font-size: 1.5vh;
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        body > div > form {
            margin: 3vh 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        body > div > form div {
            margin: 1vh;
        }

        body > div > form > button {
            padding: 1vh;
            font-weight: bold;
            border-radius: 1vh;
            border: 0.1vh solid white;
            background: none;
            font-size: 1.5vh;
            color: white;
        }

        body > div > form > button:hover {
            background-color: rgba(245, 245, 245, 0.2);
            cursor: pointer;
        }

        body > div > form > div input,
        body > div > form > div input:focus,
        body > div > form > div input:focus-visible {
            font-size: 1.5vh;
            padding: 1vh;
            border-radius: 1vh;
            border: 0.1vh solid white;
            background: none;
            outline: none;
            color: white;
            font-weight: bold;
            padding-left: 3.5vh;
        }

        body > div > form > div input::placeholder {
            color: white;
        }
        
        body > div > form > div span.material-symbols-outlined {
            display: flex;
            font-size: 3.5vh;
            align-items: center;
            color: white;
            font-weight: lighter;
        }

        body > div > form > div span.material-symbols-outlined::after {
            position: absolute;
            padding-right: 0.5vh;
        }

        body > div > form > div span#text::after {content: "account_circle";}
        body > div > form > div span#password::after {content: "lock";}
    </style>
</head>
<body>
    <div>
        <h1>S'identifier</h1>
        <form action="Authent" method="post">
            <div>
                <span id="text" class="material-symbols-outlined"><input type="text" placeholder="Entrez votre nom d'utilisateur" name="uname" required></span>
            </div>
            <div>
                <span id="password" class="material-symbols-outlined"><input type="password" placeholder="Entrez votre mot de passe" name="upswd" required></span>
            </div>
            <button type="submit">Se connecter</button>
        </form>
        <form action="Control?page=authentShortcut" method="post">
            <button type="submit">Je me suis deja connecté !</button>
        </form>
        <a href="createAccount.jsp">Creer ton compte ici !</a>
    </div>
</body>
</html>