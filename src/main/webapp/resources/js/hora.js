const getRemainingTime = deadline => { //esta variable recibira el tiempo faltante
    let now = new Date(),
    //me devuelve el tiempo en milisegundos
        remainTime = (new Date(deadline) - now + 1000) / 1000,
        remainSeconds = ('0' + Math.floor(remainTime % 60)).slice(-2),
        remainMinutes = ('0' + Math.floor(remainTime / 60 % 60)).slice(-2),
        remainHours = ('0' + Math.floor(remainTime / 3600 % 24)).slice(-2);
  
    return {
      remainSeconds,
      remainMinutes,
      remainHours,

      remainTime
    }
  };
  
  const countdown = (deadline,elem,finalMessage) => {
    const el = document.getElementById(elem);
  
    const timerUpdate = setInterval( () => {
      let t = getRemainingTime(deadline);
      el.innerHTML = `${t.remainHours}h:${t.remainMinutes}m:${t.remainSeconds}s`;
  
      if(t.remainTime <= 1) {
        clearInterval(timerUpdate);
        el.innerHTML = finalMessage;
      }
  
    }, 1000)
  };


    const fecha = new Date();
    console.info(fecha)
    //fecha.setMinutes(216000); //un dia
    fecha.setSeconds(60);

  countdown(fecha, 'clock', 'Tu tiempo para juegar este juego a finalizado');
  