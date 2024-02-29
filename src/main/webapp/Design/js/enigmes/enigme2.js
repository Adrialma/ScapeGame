function sendAnswer(imageId) {
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = 'Enigme'; // Assurez-vous que le mapping de servlet est correct

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'answer';
    hiddenField.value = imageId;

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}
