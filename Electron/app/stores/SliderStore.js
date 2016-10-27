import BaseStore from "../services/BaseStore"

function SliderStore() {
    const service = BaseStore({
      action: "",
      motionDetection: false
    });

    service.nextSlide = function () {
      this.data = {
        action: "next"
      }
    };

    service.prevSlide = function () {
      this.data = {
        action: "prev"
      }
    };

    service.motionDetection = function(){
      this.data = {
        motionDetection: !this.data.motionDetection
      }
    };

    return service;
}

module.exports = SliderStore();
