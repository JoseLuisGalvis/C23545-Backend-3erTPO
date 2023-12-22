// *****************************************************************//
// 1er Script Video N° 31 02122023
const nuevoOrador = () => {

    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const tema = document.getElementById('tema').value;

    // Objeto 
    const orador = {
        nombre,
        apellido,
        tema
    };

    // Post al Servidor
    // Paso # 1: Preparo la Peticion
    const respuesta = fetch('http://localhost:8080/OradoresC23545E6/api/orador', {
        method: 'POST',
        body: JSON.stringify(orador)
    });

    // Paso # 2: Intento Resolver la Promesa
    respuesta
        .then(response => response.json())
        .then(respuesta => {
            // Actualizar el div del html con la informacion
            // dibujarTabla(users)
            alert(` Se ha Dado de Alta el Orador Id:${respuesta.id} `);
            listarOradores();
        })
        .catch(error => console.log(error))
}


// *****************************************************************//
// Listado Oradores
function listarOradores() {

    // Paso # 1: preparo la peticion
    const respuesta = fetch('http://localhost:8080/OradoresC23545E6/api/orador');

    //2 intento reosolver la promesa
    respuesta
        .then(response => response.json())
        .then(oradores => {
            setOradores(oradores);
            //actualizar el div del html con la informacion
            dibujarTabla(oradores)
        })
        .catch(error => console.log(error))
}

function dibujarTabla(data) {
    const rows = dibujarFilas(data);
    document.getElementById('usersRows').innerHTML = rows;
}

function dibujarFilas(oradores) {
    let rows = ``;
    for (let orador of oradores) {//ctrl+d ctr+f2
        //console.log(user)
        rows += `
        <tr>
            <th scope="row">${orador.id}</th>
            <td>${orador.nombre}</td>
            <td>${orador.apellido}</td>
            <td>${orador.tema}</td>
            <td>
                <button onClick='eliminarOrador(${orador.id})' class="btn btn-danger">Eliminar</button>
            </td>
            <td>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="setId(${orador.id})">
                    Editar
                </button>
            </td>
        </tr>
        `
    }
    return rows;
}


// *****************************************************************//
// Eliminar Orador
const eliminarOrador = (id) => {

    const respuesta = fetch(`http://localhost:8080/OradoresC23545E6/api/orador?id=${id}`, {
        method: 'DELETE',
    });

    respuesta
        .then(response => response.json())
        .then(respuesta => {
            //actualizar el div del html con la informacion
            alert(` Se ha Eliminado el Orador Id:${respuesta.id} `);
            listarOradores();
        })
        .catch(error => console.log(error))

}


// *****************************************************************//
// Editar Orador

let oradorId;
let oradores = [];
let oradorActual;


const setId = (id) => {
    oradorId = id;
    // alert(oradorId);

    const orador = oradores.find(o => o.id === id);

    oradorActual = orador;

    document.getElementById('nombreActualizar').value = oradorActual.nombre;
    document.getElementById('apellidoActualizar').value = oradorActual.apellido;
    document.getElementById('temaActualizar').value = oradorActual.tema;
}


const setOradores = (nuevosOradores) => {
    oradores = nuevosOradores;
    // alert(JSON.stringify(oradores));
}


const actualizarOrador = () => {

    if (!oradorActual) {
        return;
    }

    const nombre = document.getElementById('nombreActualizar').value;
    const apellido = document.getElementById('apellidoActualizar').value;
    const tema = document.getElementById('temaActualizar').value;

    const orador = {
        nombre,
        apellido,
        tema
    };

    //post al servidor
    //1 preparo la peticion
    const respuesta = fetch(`http://localhost:8080/OradoresC23545E6/api/orador?id=${oradorActual.id}`, {
        method: 'PUT',
        body: JSON.stringify(orador)
    });

    //2 intento reosolver la promesa
    respuesta
        .then(response => response.json())
        .then(respuesta => {
            //actualizar el div del html con la informacion
            alert(` Se ha Actualizado el Orador id: ${respuesta.id} `);
            listarOradores();
        })
        .catch(error => console.log(error))
}


document.getElementById('btnGrabar').addEventListener('click', nuevoOrador);
document.getElementById('btnListado').addEventListener('click', listarOradores);

//listarOradores();