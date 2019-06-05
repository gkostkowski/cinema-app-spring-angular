import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {RouterTestingModule} from '@angular/router/testing';
import {GeneralModule} from './general/general.module';

describe('AppComponent', () => {
    let fixture: ComponentFixture<AppComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                AppComponent
            ],
            imports: [
                RouterTestingModule,
                GeneralModule

            ],
            providers: [],
        }).compileComponents();


        fixture = TestBed.createComponent(AppComponent);
        fixture.detectChanges();
    }));

    it('should create the app', async(() => {
        const app = fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    }));

    it('should render navigation bar', async(() => {
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('app-nav ul li a').textContent).toContain('Screenings');
    }));
});
