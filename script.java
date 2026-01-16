const video = document.getElementById('camera');
const captureBtn = document.getElementById('capture');
const downloadBtn = document.getElementById('download');
const gallery = document.getElementById('gallery');
let lastPhoto = null;

// Aktifkan kamera
navigator.mediaDevices.getUserMedia({ video: true })
  .then(stream => {
    video.srcObject = stream;
  })
  .catch(err => {
    alert("Kamera tidak bisa diakses: " + err);
  });

// Ambil foto
captureBtn.addEventListener('click', () => {
  const canvas = document.createElement('canvas');
  canvas.width = video.videoWidth;
  canvas.height = video.videoHeight;
  const ctx = canvas.getContext('2d');
  ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

  const img = document.createElement('img');
  img.src = canvas.toDataURL('image/png');
  gallery.appendChild(img);

  // Simpan foto terakhir untuk download
  lastPhoto = img.src;
});

// Download foto terakhir
downloadBtn.addEventListener('click', () => {
  if (lastPhoto) {
    const a = document.createElement('a');
    a.href = lastPhoto;
    a.download = 'photobooth.png';
    a.click();
  } else {
    alert("Belum ada foto yang diambil!");
  }
});
