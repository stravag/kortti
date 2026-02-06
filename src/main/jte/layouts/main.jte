@import gg.jte.Content

@param String title
@param Content content

<!--suppress HtmlUnknownTarget -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">
    <link rel="stylesheet" href="/webjars/picocss__pico/2.1.1/css/pico.css">
    <link rel="stylesheet" href="/stylesheet.css">
    <script src="/webjars/htmx.org/2.0.8/dist/htmx.min.js"></script>
    <script src="/modal.js"></script>
</head>
<body>
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
    ${content}
</main>
</body>
</html>
