    //Mudança de título da pagína
    const tituloTarefaElement = document.getElementById('tituloTarefa');

    tituloTarefaElement.addEventListener('input', function() {
        const novoTitulo = tituloTarefaElement.textContent.trim();
        document.title = novoTitulo || 'To Do List';
    });

    //adicionando evento ao clicar ou pressionar enter para adicionar uma tarefa.
    const button = document.getElementById("plus-icon");
