function ModalComponent(props) {
    const modal = props.modal;
    const setModal = props.setModal;
    const message = props.message;

    return (
        <div id="modal-container" style={modal ? { transform: 'scale(1)' } : { transform: 'scale(0)' }} onClick={() => setModal(false)}>
            <div className="modal-background">
                <div className="modal" style={modal ? { transform: 'scale(1)' } : { transform: 'scale(0)' }} onClick={() => setModal(false)}>
                    <p>{message}</p>
                </div>
            </div>
        </div>

    );
}

export default ModalComponent;
