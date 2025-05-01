    //Mudança de título da pagína
    const tituloTarefaElement = document.getElementById('tituloTarefa');

    tituloTarefaElement.addEventListener('input', function() {
        const novoTitulo = tituloTarefaElement.textContent.trim();
        document.title = novoTitulo || 'To Do List';
    });

    //adicionando evento ao clicar ou pressionar enter para adicionar uma tarefa.
    document.addEventListener("DOMContentLoaded", function () {
        const input = document.getElementById("input-task");
        const button = document.getElementById("btn-add");
        const lista = document.getElementById("lista-tasks");

        function adicionarTarefa() {
            const task = input.value.trim();
            if (task === "") return;

            fetch("/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ task })
            })
            .then(response => response.json())
            .then(tarefa => {
                const li = document.createElement("li");
                li.textContent = tarefa.task;
                lista.appendChild(li);
                input.value = "";
            })
            .catch(error => console.error("Erro ao adicionar tarefa:", error));
        }

        button.addEventListener("click", adicionarTarefa);
        input.addEventListener("keypress", function (e) {
            if (e.key === "Enter") {
                adicionarTarefa();
            }
        });
    });

