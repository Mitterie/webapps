<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creer un compte</title>

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
        
        body > div > form > p {
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
        body > div > form > div span#email::after {content: "mail";}
    </style>
</head>
<body>
    <div>
        <h1>Creer ton compte !</h1>
        <form action="CreateAccount" method="post">
            <div>
                <span id="text" class="material-symbols-outlined"><input type="text" placeholder="Entrez votre nom d'utilisateur" name="ulogin" required></span>
            </div>
            <div>
                <span id="password" class="material-symbols-outlined"><input type="password" placeholder="Entrez votre mot de passe" name="upswd" required></span>
            </div>
            <div>
                <span id="email" class="material-symbols-outlined"><input type="email" placeholder="Entrez votre adresse email" name="uemail" required></span>
            </div>
            <button type="submit">Creer</button>
            <p>Si la création du compte réussi vous serait rediriger sur la page de connexion</p>
            <p>Sinon vous reviendrai sur cette page apres avoir cliquer sur "Creer"</p>
        </form>
        <a href="/Mitterie/">Retour</a>
    </div>
</body>
</html>