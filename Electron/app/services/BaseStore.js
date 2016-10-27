import Rx from "rx";

function deepFreeze(o) {
    Object.freeze(o);

    Object.getOwnPropertyNames(o).forEach(function (prop) {
        if (o[prop] !== null
            && (typeof o[prop] === "object" || typeof o[prop] === "function")
            && !Object.isFrozen(o[prop])) {
            deepFreeze(o[prop]);
        }
    });

    return o;
}

function BaseStore(initialData) {
    let _observer = null;
    return {
        get data() {
            return initialData;
        },
        set data(t) {
            initialData = deepFreeze(t);
            _observer.next(initialData);
        },
        data$: Rx.Observable.create(observer => _observer = observer).share()
    }
}

export default BaseStore;