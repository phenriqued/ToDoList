    const tituloTarefaElement = document.getElementById('tituloTarefa');

    tituloTarefaElement.addEventListener('input', function() {
        const novoTitulo = tituloTarefaElement.textContent.trim();
        document.title = novoTitulo || 'To Do List';
    });