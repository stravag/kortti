@import gg.jte.Content

@param String title
@param String subTitle
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="/webjars/picocss__pico/2.1.1/css/pico.css">
    <style>
        /* Hide the spinner by default */
        .spinner {
            display: none;
        }

        /* Show it only when the button is in a request state */
        .htmx-request .spinner {
            display: inline;
        }
    </style>
    <script src="/webjars/htmx.org/2.0.8/dist/htmx.min.js"></script>
    <script src="/webjars/htmx-ext-sse/2.2.4/dist/sse.js"></script>
</head>
<body hx-boost="true">
<main class="container">
    <nav>
        <ul>
            <li><strong>${title}</strong></li>
        </ul>
        <ul>
            <li><a href="/">Cards</a></li>
            <li><a href="/memes">Memes</a></li>
            <li><a href="/games">Games</a></li>
            <li><a href="/charts">Charts</a></li>
        </ul>
    </nav>
    <section>
        ${subTitle}
    </section>
    ${content}
</main>
</body>
</html>
