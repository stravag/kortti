@import ch.ranil.kortti.domain.chart.DatasetType

@template.layouts.main(
title = "Charts",
subTitle = "HTMX in combination with third-party JavaScript Library Chart.js",
content = @`
    <select id="data-selector"
            name="datasetType"
            hx-get="/charts/dataset"
            hx-trigger="change"
            hx-swap="none"
            hx-headers='{"Accept": "application/json"}'
            hx-on::after-request="updateMyChart(JSON.parse(event.detail.xhr.response))"
            aria-label="Select dataset type">
        @for (var datasetType: DatasetType.getEntries())
            <option value="${datasetType.name()}">${datasetType.name()}</option>
        @endfor
    </select>

    <div id="chart-container">
        <canvas id="myChart"></canvas>
    </div>

    <script type="module">
        // Dynamically import Chart.js from a CDN
        import Chart from 'https://esm.sh/chart.js/auto';

        const ctx = document.getElementById('myChart');
        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [],
                datasets: [{
                    label: 'Dataset',
                    data: [],
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            }
        });

        window.updateMyChart = (data) => {
            myChart.data.labels = data.labels;
            myChart.data.datasets[0].data = data.datasets[0].data;
            myChart.update();
        };

        // trigger htmx once chart is ready
        setTimeout(() => {
            htmx.trigger("#data-selector", "change");
        }, 50);
    </script>
`)
