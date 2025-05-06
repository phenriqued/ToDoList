    //Mudança de título da pagína
    const tituloTarefaElement = document.getElementById('tituloTarefa');

    tituloTarefaElement.addEventListener('input', function() {
        const novoTitulo = tituloTarefaElement.textContent.trim();
        document.title = novoTitulo || 'To Do List';
    });

    //adicionando evento ao clicar ou pressionar enter para adicionar uma tarefa.
    const input = document.getElementById("input-task");
    const button = document.getElementById("btn-add");
    const lista = document.getElementById("lista-tasks");
    let updateTaskId = null;

    function adicionarTarefa() {
        const task = input.value.trim();
        if (task === "") return;

        if (updateTaskId !== null) {
            fetch(`/task/${updateTaskId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ task })
            })
            .then(() => {
                carregarTarefas();
                input.value = "";
                updateTaskId = null;
            })
            .catch(err => console.error("Erro ao editar tarefa:", err));
        }else{
            fetch("/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ task })
            })
            .then(() => {
                adicionarElementoNaLista(task);
                input.value = "";
                carregarTarefas()
            })
            .catch(err => console.error("Erro ao adicionar tarefa:", err));
        }
    }

    button.addEventListener("click", adicionarTarefa);
    input.addEventListener("keypress", function (e) {
        if (e.key === "Enter") {
            adicionarTarefa();
        }
    });


    //Adicionando o leitura das tarefas
    window.addEventListener("DOMContentLoaded", carregarTarefas);
    function carregarTarefas() {
        fetch("/ToDoList")
            .then(response => response.json())
            .then(data => {
                lista.innerHTML = "";
                data.content.forEach(tarefa => {
                    adicionarElementoNaLista(tarefa.id, tarefa.task, tarefa.done, tarefa.favorite);
                });
            })
            .catch(err => console.error("Erro ao carregar tarefas:", err));
    }
    function adicionarElementoNaLista(id, task, done, favorite) {
        const div = document.createElement("div");
        div.classList.add("task-item");

        div.setAttribute("data-id", id);

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.classList.add("task-checkbox");
        checkbox.checked = done;

        const label = document.createElement("label");
        label.textContent = task;
        label.classList.add("task-label");
        if (done) label.style.textDecoration = "line-through";

        // Atualiza visual ao clicar no checkbox para o futuro do DONE
        checkbox.addEventListener("change", () => {
            label.style.textDecoration = checkbox.checked ? "line-through" : "none";
            const id = div.getAttribute("data-id");
            fetch(`/task/${id}/done`,{
                method:"PUT"
            })
            .then(() => carregarTarefas())
            .catch(err => console.error("Erro ao definir como concluído", err));

        });

         // Estrela
            const star = document.createElement("span");
            star.innerHTML = "⭐";
            star.classList.add("task-favorite");
            //if (favorite) star.classList.add("favorited");

            star.addEventListener("click", () => {
                star.classList.toggle("task-favorite");
                const id = div.getAttribute("data-id");
                fetch(`/task/${id}/favorite`,{
                    method:"PUT"
                })
                .then(() => carregarTarefas())
                .catch(err => console.error("Erro ao marcar como favorito:", err));
            });

            // Lápis
            const edit = document.createElement("span");
            edit.innerHTML = "✏️";
            edit.classList.add("task-edit");
            edit.addEventListener("click", () => {
                document.getElementById("input-task").value = task;
                updateTaskId = id;
            });

            // Lixeira
            const trash = document.createElement("span");
            trash.innerHTML = "🗑️";
            trash.classList.add("task-delete");

            trash.addEventListener("click", () => {
                div.remove();
                fetch(`/task/${id}/remove`,{
                    method:"DELETE"
                })
            });

        div.appendChild(checkbox);
        div.appendChild(label);
        div.appendChild(star);
        div.appendChild(edit);
        div.appendChild(trash);

        lista.appendChild(div);

    }
    //Carregar a lista a cada um hora
    setInterval(carregarTarefas, 3600000);
